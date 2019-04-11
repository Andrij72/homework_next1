package com.database.operations;

import com.database.data.Customer;
import com.database.data.JdbcConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static final String SELECT_ALL = "SELECT * FROM customers";
    private static final String SELECT_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String INSERTquery = "INSERT INTO customers(name, age) VALUES(?, ?)";
    private static final String DELETE_ID = "DELETE FROM customers WHERE id = ?";
    private static final String UPDATEquery = "UPDATE customers SET name = ?, age = ? WHERE id = ?";

    public Customer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Customer customer = createCustomer(resultSet);
       connection.close();
        return customer;
    }

    public List<Customer> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Customer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createCustomer(resultSet));
        }
        connection.close();
        return result;
    }

    public void delete(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
       PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID);
        preparedStatement.setInt(1, id);
        connection.close();
    }

    public void insert(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERTquery);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());

       connection.close();
    }

    public void update(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATEquery);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setInt(3, object.getId());

        connection.close();
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
