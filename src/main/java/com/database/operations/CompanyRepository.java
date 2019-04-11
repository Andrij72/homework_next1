package com.database.operations;

import com.database.data.Company;
import com.database.data.DBCloseConnect;
import com.database.data.JdbcConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    // Qwery1 - SELECT_ALL, Qwery2 - SELECT_ID , Qwery3 - INSERT;  Qwery4 - DELETE;  Qwery5 - UPDATE;
    private static final String Qwery1 = "SELECT * FROM companies";
    private static final String Qwery2 = "SELECT * FROM companies WHERE id = ?";
    private static final String Qwery3 = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String Qwery4 = "DELETE FROM companies WHERE id = ?";
    private static final String Qwery5 = "UPDATE companies SET name = ?, address = ? WHERE id = ?";

    public List<Company> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        if (connection == null) throw new AssertionError();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Qwery1);
        List<Company> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(createCompany(resultSet));
        }

        DBCloseConnect.close(resultSet, connection, statement);
        return result;
    }

    public Company selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        if (connection == null) throw new AssertionError();
        PreparedStatement preparedStatement = connection.prepareStatement(Qwery2);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Company company = createCompany(resultSet);
        DBCloseConnect.close(resultSet, connection, preparedStatement);
        return company;
    }


    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        if (connection == null) throw new AssertionError();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(Qwery4);
        preparedStatement.setInt(1, id);

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void insert(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        if (connection == null) throw new AssertionError();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(Qwery3);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void update(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        if (connection == null) throw new AssertionError();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(Qwery5);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());
        preparedStatement.setInt(3, object.getId());

        DBCloseConnect.close(connection, preparedStatement);
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("address"));
    }
}
