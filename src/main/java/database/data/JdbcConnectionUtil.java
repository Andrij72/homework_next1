package database.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(JdbcConnectionUtil.class.getName());

    public static Connection getConnection () throws SQLException {
        Connection dbConnection = null;
        try {
            FileInputStream fis;
            Properties property = new Properties();
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            return DriverManager.getConnection(property.getProperty("db.url"),
                    property.getProperty("db.login"),
                    property.getProperty("db.password"));
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    LOGGER.setLevel(Level.WARNING);
                    LOGGER.warning("Сonnection close error: " + e);
                }
            }
        }
        return dbConnection;
    }
}
