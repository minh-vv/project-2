package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    @Override
    public DistrictEntity findDistrict(Long districtId) {
        DistrictEntity district = null;
        String sql = "SELECT * FROM district WHERE id = " + districtId.toString();
        System.out.println(sql);

        try (Connection conn = ConnectionUtil.getConnection();
             java.sql.Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {

            if (rs.next()) { 
                district = new DistrictEntity();
                district.setId(rs.getLong("id"));
                district.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }

        return district;
    }
}
