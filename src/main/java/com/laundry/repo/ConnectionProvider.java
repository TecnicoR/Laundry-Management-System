package com.laundry.repo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

    private ConnectionProvider() {
        super();
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/laundrydb",
                    "root",
                    "system");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
