package com.kato.library.library_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTestRunner {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/main/resources/library.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Database connected successfully!");
                System.out.println("URL: " + url);
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }
}
