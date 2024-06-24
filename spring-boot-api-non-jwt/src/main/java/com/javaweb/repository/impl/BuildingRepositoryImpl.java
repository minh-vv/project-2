package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	@Override
	public List<BuildingEntity> findAll(Map<Object,Object> params, List<String> typeCode) {
		
		List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();
		
		StringBuilder sql = new StringBuilder(" SELECT b.* From building b \n ");
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		querySqlJoin(params,typeCode,sql);
		querySqlNormal(params,where);
		querySqlSpecial(params,typeCode,where);
		
		sql.append(where).append(" group by b.id ");
		
		System.out.print(sql);
		
	      try(Connection conn = ConnectionUtil.getConnection();
	    		  java.sql.Statement stm = conn.createStatement();
	    		  ResultSet rs = stm.executeQuery(sql.toString());){
	          
	    	  while (rs.next()) {
	    		  BuildingEntity buildingEntity = new BuildingEntity();
	    		  buildingEntity.setId(rs.getLong("id"));
	    		  buildingEntity.setName(rs.getString("name"));
	    		  
	    		  buildingEntity.setWard(rs.getString("ward"));
	    		  buildingEntity.setStreet(rs.getString("street"));
	    		  buildingEntity.setDistrictId(rs.getLong("districtId"));	
	    		  
	    		  buildingEntity.setNumberOfBasement(rs.getLong("numberOfBasement"));
	    		  
	    		  buildingEntity.setManagerName(rs.getString("managerName"));
	    		  buildingEntity.setManagerPhoneNumber(rs.getString("managerPhoneNumber"));
	    		  
	    	
	    		  buildingEntity.setFloorArea(rs.getLong("floorArea"));
	    		  
	    		  buildingEntity.setRentPrice(rs.getLong("rentprice"));
	    		 
	    		  buildings.add(buildingEntity);
	    	  }
 	        } catch (SQLException e) {
	           e.printStackTrace();
	            System.out.println("Connected database failed...");
	        } 		
	      return buildings;
	}
	public void querySqlJoin(Map<Object,Object> params, List<String> typeCode, StringBuilder join) {
		String staffId = (String)params.get("staffId");
		if (StringUtil.checkData(staffId)) {
			join.append(" join assignmentbuilding as on b.buildingid = as.buildingid ");
		}
		String rentAreaFrom =(String)params.get("rentAreaFrom");
		String rentAreaTo =(String)params.get("rentAreaTo");
		if(StringUtil.checkData(rentAreaFrom) || StringUtil.checkData(rentAreaTo)) {
			join.append(" join rentarea ra on b.id = ra.buildingid");
		}
		
		if (typeCode != null && !typeCode.isEmpty()) {
			join.append(" join buildingrenttype brt on brt.buildingid = b.id \n ");
			join.append(" join renttype rt on rt.id = brt.renttypeid \n ");
		}
	}
	
	public void querySqlNormal(Map<Object,Object> params, StringBuilder where) {
		for(Map.Entry<Object, Object> item : params.entrySet()) {
			String key = item.getKey().toString();
			if(!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea") && !key.startsWith("rentPrice")) {
				String value = item.getValue().toString();
				if(NumberUtil.isNumber(value) == true) {
					where.append(" and b." + key.toLowerCase() + " = " + value);
				}
				else where.append(" and b."+ key.toLowerCase() + " like '%"+ value+ "%' ");
			}
		}
	}
	
	public void querySqlSpecial(Map<Object,Object> params, List<String> typeCode,StringBuilder where) {
		String rentAreaFrom =(String)params.get("rentAreaFrom");
		String rentAreaTo =(String)params.get("rentAreaTo");
		if(StringUtil.checkData(rentAreaFrom)) {
			where.append(" and ra.value >=  " + rentAreaFrom);
		}
		if(StringUtil.checkData(rentAreaTo)) {
			where.append(" and ra.value <=  " + rentAreaTo);
		}
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkData(staffId)) {
			where.append(" and as.staffId = " + staffId);
		}
		String rentPriceFrom =(String)params.get("rentPriceFrom");
		String rentPriceTo =(String)params.get("rentPriceTo");
		if(StringUtil.checkData(rentPriceFrom)) {
			where.append(" and b.rentprice >=  " + rentPriceFrom);
		}
		if(StringUtil.checkData(rentPriceTo)) {
			where.append(" and b.rentprice <=  " + rentPriceTo);
		}
		//java 7
		if (typeCode != null && !typeCode.isEmpty()) {
		    List<String> code = new ArrayList<>();
		    for (String it : typeCode) {
		        code.add("'" + it + "'");
		    }
		    where.append(" and rt.code in (" + String.join(",", code) + ") \n");
		}
		// java 8 
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" and (");
			where.append(typeCode.stream().map(item -> " rt.code like '%" + item +"%'").collect(Collectors.joining(" or ")));
			where.append(") ");
		}

	}
	
	
	
	
}
