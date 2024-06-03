package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DatabaseConfig;
import com.javaweb.repository.entity.BuildingEntity;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	@Override
	public List<BuildingEntity> findAll(Map<Object,Object> params, List<String> typeCode) {
		
		List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();
		
		String sql = "SELECT b.* From building b \n ";
		sql += joinProcess(params, typeCode) + whereProcess(params, typeCode) ;		
		
		System.out.print(sql);
		
	      try(Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL,DatabaseConfig.USER,DatabaseConfig.PASS);
	    		  java.sql.Statement stm = conn.createStatement();
	    		  ResultSet rs = stm.executeQuery(sql);){
	          
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
	public String joinProcess(Map<Object,Object> params, List<String> typeCode) {
		String join ="";
		if (params.get("rentAreaFrom") != null || params.get("rentAreaTo") != null  ) {
			join +=" join rentarea ra on b.id = ra.buildingid \n";
		}
		if (params.get("staffId") != null && params.get("districtId") != "" ) {
			join +=" join assignmentbuilding a on b.buildingid = a.buildingid \n";
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			join +=" join buildingrenttype brt on brt.buildingid = b.id \n ";
			join +=" join renttype rt on rt.id = brt.renttypeid \n ";
		}
		
		return join;
	}
	public String whereProcess(Map<Object,Object> params, List<String> typeCode) {
		
		String where = " where 1 = 1 ";
		
		if (params.get("name") != null && params.get("name") != "" ) {
			where += " and b.name like '%" + params.get("name") + "%' ";	
		}
		if (params.get("ward") != null && params.get("ward") != "" ) {
			where += " and b.ward like '%" + params.get("ward") + "%' ";	
		}
		if (params.get("street") != null && params.get("street") != "" ) {
			where += " and b.street like '%" + params.get("street") + "%' ";	
		}
		if (params.get("managerName") != null && params.get("managerName") != "" ) {
			where += " and b.managername like '%" + params.get("managerName") + "%' ";	
		}
		if (params.get("managerPhoneNumber") != null && params.get("managerPhoneNumber") != "" ) {
			where += " and b.managerphonenumber like '%" + params.get("managerPhoneNumber") + "%' ";	
		}
		if (params.get("level") != null) {
			where += " and b.level = " + params.get("level").toString();
		}
		if (params.get("floorArea") != null) {
			where += " and b.floorarea = " + params.get("floorArea").toString();
		}
		if (params.get("rentAreaFrom") != null) {
			where += " and b.rentarea >= " + params.get("rentAreaFrom").toString();
		}
		if (params.get("rentAreaTo") != null) {
			where += " and ra.rentarea <= " + params.get("rentAreaTo").toString();
		}
		if (params.get("rentPriceFrom") != null) {
			where += " and b.rentprice >= " + params.get("rentPriceFrom").toString();
		}
		if (params.get("rentPriceTo") != null) {
			where += " and b.rentprice <= " + params.get("rentPriceTo").toString();
		}
		if (params.get("districtId") != null ) {
			where += " and b.districtid = " + params.get("districtId").toString();
		}
		if (params.get("staffId") != null ) {
			where += " and a.staffid = " + params.get("staffId").toString();
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			where += " and rt.code in (";
			for (int i = 0; i <typeCode.size();i++) {
				where += "'";
				where += typeCode.get(i);
				where += "'";
				if (i < typeCode.size() - 1) {
					where += ",";
				}
			}
			where += ") \n";
		}
		return where;
		
	}
	
	
}
