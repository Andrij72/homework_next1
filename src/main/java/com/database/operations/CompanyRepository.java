package com.database.operations;

import com.database.data.Company;
import com.database.data.JdbcConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private static final String SELECT_ALL  = "SELECT * FROM companies";
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String INSERT= "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String DELETE_ID = "DELETE FROM companies WHERE id = ?";
    private static final String UPDATE_ID = "UPDATE companies SET name = ?, address = ? WHERE id = ?";

    public List<Company> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Company> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(createCompany(resultSet));
        }
        connection.close();
        return result;
    }

    public Company selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Company company = createCompany(resultSet);
        connection.close();
        return company;
    }


    public void deleteById(int id) throws SQLException {

        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID);
        preparedStatement.setInt(1, id);
        connection.close();
    }

    public void insert(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());
        connection.close();
    }

    public void update(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ID);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());
        preparedStatement.setInt(3, object.getId());
        connection.close();
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("address"));
    }
}
