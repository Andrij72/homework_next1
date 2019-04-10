package com.database.operations;

import com.database.data.Customer;
import com.database.data.DBCloseConnect;
import com.database.data.JdbcConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrud {

    // Qwery1 - SELECT_ALL, Qwery2 - SELECT_ID , Qwery3 - INSERT;  Qwery4 - DELETE;  Qwery5 - UPDATE;
    private static final String QUERY1 = "SELECT * FROM customers WHERE id = ?";
    private static final String QUERY2 = "INSERT INTO customers(name, age) VALUES(?, ?)";
    private static final String QUERY3 = "DELETE FROM customers WHERE id = ?";
    private static final String QUERY4 = "UPDATE customers SET name = ?, age = ? WHERE id = ?";

    public Customer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY1);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Customer customer = createCustomer(resultSet);
       DBCloseConnect.close(resultSet, connection, preparedStatement);
        return customer;
    }

    public List<Customer> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM customers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Customer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createCustomer(resultSet));
        }
        DBCloseConnect.close(resultSet, connection, statement);
        return result;
    }

    public void delete(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
       PreparedStatement preparedStatement = connection.prepareStatement(QUERY3);
        preparedStatement.setInt(1, id);
        DBCloseConnect.close(connection, preparedStatement);
    }

    public void insert(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY2);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void update(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY4);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setInt(3, object.getId());

        DBCloseConnect.close(connection, preparedStatement);
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
