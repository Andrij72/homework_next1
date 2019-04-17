package database.operations;

import database.data.Developer;
import database.data.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperCrudoprations {

    private static final Logger LOGGER = Logger.getLogger(DeveloperCrudoprations.class.getName());
    private static final String SELECT_ID = "SELECT * FROM developers WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM developers";
    private static final String INSERT = "INSERT INTO developers(name, age, gender, salary) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE developers SET name = ?, age = ?, gender = ?, salary = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";

    private String pathToTheQuery = "src/main/resources/";

    public Developer selectById (int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Developer developer;
        try {
            connection = JdbcConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            developer = createDeveloper(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return developer;
    }

    public List <Developer> selectAll () throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List <Developer> reslist = new ArrayList <>();
        try {
            connection = JdbcConnectionUtil.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                reslist.add(createDeveloper(resultSet));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return reslist;
    }

    public void deleteById (int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insert (Developer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.setString(3, object.getSex());
            preparedStatement.setDouble(4, object.getSalary());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void update (Developer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.setString(3, object.getSex());
            preparedStatement.setDouble(4, object.getSalary());
            preparedStatement.setInt(5, object.getId());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List <Developer> getQueryDevelopersOnProject (String projectName) throws SQLException {
        return selectAllByCondition(projectName, pathToTheQuery + "queryDevelopersOnProject.sql");
    }

    public List <Developer> getMiddleDevelopers (String skillLevel) throws SQLException {
        return selectAllByCondition(skillLevel, pathToTheQuery + "queryMiddleDevelopers.sql");
    }

    public List <Developer> getListOfDevelopersByIndustry (String branchDevelopment) throws SQLException {
        return selectAllByCondition(branchDevelopment, pathToTheQuery + "queryAllDevelopers.sql");
    }

    public double getSalaryOfDevelopersByProject (String projectName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double salary;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql = null;
            try {
                sql = new Scanner(new File(pathToTheQuery + "querySalaryDevelopers.sql"))
                        .useDelimiter("").next();
            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage());
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, projectName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            salary = resultSet.getDouble(1);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return salary;
    }

    private List <Developer> selectAllByCondition (String conditionalField, String pathToSql) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List <Developer> result = new ArrayList <>();
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql = null;
            try {
                sql = new Scanner(new File(pathToSql)).useDelimiter("").next();
            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage());
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, conditionalField);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(createDeveloper(resultSet));
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    private Developer createDeveloper (ResultSet resultSet) throws SQLException {
        return new Developer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("sex"),
                resultSet.getDouble("salary"));
    }
}
