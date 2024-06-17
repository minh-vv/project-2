package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "Minh@2004";
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			return conn;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
