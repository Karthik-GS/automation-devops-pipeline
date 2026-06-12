package com.config.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {
    public static String getDbData(String query, String columnName) throws Exception {
        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        Connection conn = DriverManager.getConnection(dbUrl, "postgres", "password123");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String val = "";
        if (rs.next()) {
            val = rs.getString(columnName);
        }
        conn.close();
        return val;
    }
}
