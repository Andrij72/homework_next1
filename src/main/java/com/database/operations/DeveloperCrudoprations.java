package com.database.operations;

import com.database.data.DBCloseConnect;
import com.database.data.Developer;
import com.database.data.JdbcConnectionUtil;
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

    public Developer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Developer developer = createDeveloper(resultSet);

        DBCloseConnect.close(resultSet, connection, preparedStatement);
        return developer;
    }

    public List<Developer> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Developer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createDeveloper(resultSet));
        }

        DBCloseConnect.close(resultSet, connection, statement);
        return result;
    }

    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setInt(1, id);

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void insert(Developer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setString(3, object.getSex());
        preparedStatement.setDouble(4, object.getSalary());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void update(Developer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setString(3, object.getSex());
        preparedStatement.setDouble(4, object.getSalary());
        preparedStatement.setInt(5, object.getId());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public List<Developer> getQueryDevelopersOnProject(String projectName) throws SQLException {
        return selectAllByCondition(projectName, pathToTheQuery + "queryDevelopersOnProject.sql");
    }

    public List<Developer> getMiddleDevelopers(String skillLevel) throws SQLException {
        return selectAllByCondition(skillLevel, pathToTheQuery + "queryMiddleDevelopers.sql");
    }

    public List<Developer> getListOfDevelopersByIndustry(String branchDevelopment) throws SQLException {
        return selectAllByCondition(branchDevelopment, pathToTheQuery + "queryAllDevelopers.sql");
    }

    public double getSalaryOfDevelopersByProject(String projectName) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToTheQuery + "querySalaryDevelopers.sql"))
                    .useDelimiter("").next();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, projectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double salary = resultSet.getDouble(1);

        DBCloseConnect.close(resultSet, connection, preparedStatement);
        return salary;
    }

    private List<Developer> selectAllByCondition(String conditionalField, String pathToSql) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToSql)).useDelimiter("").next();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, conditionalField);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Developer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createDeveloper(resultSet));
        }

        DBCloseConnect.close(resultSet, connection, preparedStatement);
        return result;
    }

    private Developer createDeveloper(ResultSet resultSet) throws SQLException {
        return new Developer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("sex"),
                resultSet.getDouble("salary"));
    }
}
