package database.operations;

import database.data.Customer;
import database.data.JdbcConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository{

    private static final String SELECT_ALL  = "SELECT * FROM companies";
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String INSERT = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String DELETE_ID = "DELETE FROM companies WHERE id = ?";
    private static final String UPDATE_ID = "UPDATE companies SET name = ?, address = ? WHERE id = ?";

@Override
    public Customer selectByIdCustomer(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        Customer customer;
        try {
        connection = JdbcConnectionUtil.getConnection();
        preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        customer = createCustomer(resultSet);

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
          connection = JdbcConnectionUtil.getConnection();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(SELECT_ALL);
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

    @Override
    public void deleteCustomer(Long id) throws SQLException {
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
@Override
    public void insertCustomer(Customer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(INSERT);
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

    public boolean updateCustomer(Customer object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(UPDATE_ID);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setLong(3, object.getId());
        }finally{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
