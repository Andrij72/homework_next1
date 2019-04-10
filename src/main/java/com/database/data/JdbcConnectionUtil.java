package com.database.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JdbcConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(JdbcConnectionUtil.class.getName());
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dbmateakademy" ;
    private static final String DB_PASSWORD = "root";
    private static final String DB_USER = "root";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.warning(e.getMessage());
        }
        return null;
    }
}
