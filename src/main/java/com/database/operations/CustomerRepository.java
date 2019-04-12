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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        Customer customer;
        try {
        connection = JdbcConnectionUtil.getConnection();
        preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        customer = createCastomer(resultSet);

        }finally {
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
        return customer;
    }

    public List<Customer> selectAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List <Customer> result = new ArrayList <>();
        try {
        Connection connection = JdbcConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
          while (resultSet.next()) {
            result.add(createCustomer(resultSet));
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
        return result;
    }

    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        statement = connection.prepareStatement(DELETE_ID);
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

    public void insert(Customer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(INSERTquery);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        }finally{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void update(Customer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(UPDATEquery);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setInt(3, object.getId());
        }finally{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
