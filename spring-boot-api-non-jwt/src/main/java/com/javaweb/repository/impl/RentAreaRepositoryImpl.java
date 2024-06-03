package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DatabaseConfig;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	public List <RentAreaEntity> findRentArea(Long buildingId){
		
		List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();
		
		String sql = " SELECT * from rentarea ";
		String where =" where buildingId = " + buildingId.toString();
		sql += where;
		System.out.println(sql);
		
		try(Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL,DatabaseConfig.USER,DatabaseConfig.PASS);
	    		  java.sql.Statement stm = conn.createStatement();
	    		  ResultSet rs = stm.executeQuery(sql);){
			
			while (rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				
				
				rentAreaEntity.setValue(rs.getLong("value"));
				
				rentAreas.add(rentAreaEntity);
			}
			
			
		}
		catch (SQLException e) {
	           e.printStackTrace();
	            System.out.println("Connected database failed...");
	        } 	
		
		return rentAreas;
	}

}
