package com.database.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(JdbcConnectionUtil.class.getName());
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection dbConnection = null;
        try {
            FileInputStream fis;
            Properties property = new Properties();
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(property.getProperty("db.url"), property.getProperty("db.login"),property.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException |IOException e ){
            LOGGER.warning(e.getMessage());
        }
            return dbConnection;
    }
}
