package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;


@Repository
@Primary

public class BuildingRepositoryImpl implements BuildingRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		
		List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();
		
		StringBuilder sql = new StringBuilder(" SELECT b.* From building b \n ");
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		querySqlJoin(builder,sql);
		querySqlNormal(builder,where);
		querySqlSpecial(builder,where);
		
		sql.append(where).append(" group by b.id ");
		
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		
	    return query.getResultList();
	}
	public void querySqlJoin(BuildingSearchBuilder builder, StringBuilder join) {
		Long staffId = builder.getStaffId();
		if (staffId != null) {
			join.append(" join assignmentbuilding as on b.buildingid = as.buildingid ");
		}
		
		if (builder.getTypeCode() != null && !builder.getTypeCode().isEmpty()) {
			join.append(" join buildingrenttype brt on brt.buildingid = b.id \n ");
			join.append(" join renttype rt on rt.id = brt.renttypeid \n ");
		}
	}
	
	public void querySqlNormal(BuildingSearchBuilder builder, StringBuilder where) {

		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields(); // Lay ten cac field cua obj dua vao 1 mang;
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice")) {
					// Do lay tung field nen khong biet kieu du lieu => obj
					Object value = item.get(builder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Long") ||item.getType().getName().equals("java.lang.Integer") || item.getType().getName().equals("java.lang.Float") ) {
							where.append(" and b." + fieldName.toLowerCase() + " = " + value);
						}
						else if (item.getType().getName().equals("java.lang.String")) {
							where.append(" and b."+ fieldName.toLowerCase() + " like '%"+ value+ "%' ");
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void querySqlSpecial(BuildingSearchBuilder builder,StringBuilder where) {
		Long rentAreaFrom = builder.getRentAreaFrom();
		Long rentAreaTo = builder.getRentAreaTo();
		if (rentAreaFrom != null || rentAreaTo != null) {
			where.append(" and exists (select * from rentarea ra where b.id = ra.buildingid ");
			if(rentAreaFrom != null) {
				where.append(" and ra.value >=  " + rentAreaFrom);
			}
			if(rentAreaTo != null) {
				where.append(" and ra.value <=  " + rentAreaTo);
			}
			where.append(") ");
		}
		
		Long staffId = builder.getStaffId();
		if(staffId != null) {
			where.append(" and as.staffId = " + staffId);
		}
		Long rentPriceFrom = builder.getRentPriceFrom();
		Long rentPriceTo = builder.getRentPriceTo();
		if(rentPriceFrom != null) {
			where.append(" and b.rentprice >=  " + rentPriceFrom);
		}
		if(rentPriceTo != null) {
			where.append(" and b.rentprice <=  " + rentPriceTo);
		}
//		
		// java 8 
		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" and (");
			where.append(typeCode.stream().map(item -> " rt.code like '%" + item +"%'").collect(Collectors.joining(" or ")));
			where.append(") ");
		}

	}
	
	
	
	
}
