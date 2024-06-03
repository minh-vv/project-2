package com.javaweb.repository;

public class DatabaseConfig {
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estatebasic";
    public static final String USER = "root";
    public static final String PASS = "Minh@2004";

    private DatabaseConfig() {
        // Private constructor để ngăn chặn việc tạo instance của class
    }
}
