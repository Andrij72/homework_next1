package com.database.operations;

import com.database.data.Customer;
import com.database.data.DBCloseConnect;
import com.database.data.JdbcConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static final String QUERY1_SELECT_ALL = "SELECT * FROM customers";
    private static final String QUERY2_SELECT_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String QUERY3_INSERT = "INSERT INTO customers(name, age) VALUES(?, ?)";
    private static final String QUERY4_DELETE = "DELETE FROM customers WHERE id = ?";
    private static final String QUERY5_UPDATE = "UPDATE customers SET name = ?, age = ? WHERE id = ?";

    public Customer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY2_SELECT_ID);
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
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY1_SELECT_ALL);
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
       PreparedStatement preparedStatement = connection.prepareStatement(QUERY4_DELETE);
        preparedStatement.setInt(1, id);
        DBCloseConnect.close(connection, preparedStatement);
    }

    public void insert(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY3_INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void update(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY5_UPDATE);
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
