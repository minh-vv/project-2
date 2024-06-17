package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	public List <String> findRentArea(Long buildingId){
		
		List<String> rentAreas = new ArrayList<String>();
		
		String sql = " SELECT * from rentarea where buildingId = " + buildingId.toString();
	
		System.out.println(sql);
		
		try(Connection conn = ConnectionUtil.getConnection();
	    		  java.sql.Statement stm = conn.createStatement();
	    		  ResultSet rs = stm.executeQuery(sql);){
			
			while (rs.next()) {
				Long value = rs.getLong("value");
				rentAreas.add(value.toString());
			}
			
			
		}
		catch (SQLException e) {
	           e.printStackTrace();
	            System.out.println("Connected database failed...");
	        } 	
		
		return rentAreas;
	}

}
