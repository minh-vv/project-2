package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "Minh@2004";
	
	@Override
	public List<BuildingEntity> findAll(String nameBuilding, Long numberOfBasement) {
		
		List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();
		String sql = "SELECT b.* From building b ";
		String where = " where 1=1 ";
		if (nameBuilding != null && !nameBuilding.equals("") ) {
			where += " and b.name like '%" + nameBuilding + "%' ";	
		}
		if (numberOfBasement != null) {
			where += " and b.numberofbasement =" + numberOfBasement ;
		}
		sql += where;
	      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    		  java.sql.Statement stm = conn.createStatement();
	    		  ResultSet rs = stm.executeQuery(sql);){
	          
	    	  while (rs.next()) {
	    		  BuildingEntity buildingEntity = new BuildingEntity();
	    		  buildingEntity.setId(rs.getLong("id"));
	    		  buildingEntity.setName(rs.getString("name"));
	    		  buildingEntity.setDistrictId(rs.getLong("districtid"));
	    		  buildingEntity.setRentPrice(rs.getLong("rentprice"));
	    		  buildingEntity.setStreet(rs.getString("street"));
	    		  buildingEntity.setWard(rs.getString("ward"));
	    		  buildingEntity.setNumberOfBasement(rs.getLong("numberofbasement"));
	    		  buildings.add(buildingEntity);
	    	  }
	        } catch (SQLException e) {
	           e.printStackTrace();
	            System.out.println("Connected database failed...");
	        } 		
	      return buildings;
	}
	
}
