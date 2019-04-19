package database.operations;

import database.data.Company;
import database.data.JdbcConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String INSERT = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String DELETE_ID = "DELETE FROM companies WHERE id = ?";
    private static final String UPDATE_ID = "UPDATE companies SET name = ?, address = ? WHERE id = ?";

    @Override
    public List <Company> selectAll () throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List <Company> reslist = new ArrayList <>();
        try {
            connection = JdbcConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                reslist.add(createCompany(resultSet));
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

    @Override
    public Company selectByIdCompany (Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company;
        try {
            connection = JdbcConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            company = createCompany(resultSet);
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
        return company;
    }

    @Override
    public void deleteCompany (Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_ID);
            statement.setLong(1, id);
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
    public void insertCompany (Company object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean updateCompany (Company object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ID);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
            preparedStatement.setLong(3, object.getId());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    private Company createCompany (ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("address"));
    }
}
