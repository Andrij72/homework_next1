package database.operations;

import database.data.JdbcConnectionUtil;
import database.data.Project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository{

    private static final String SELECT_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String UPDATE = "UPDATE projects SET name = ?, cost = ? date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String INSERT = "INSERT INTO projects(name, cost, date) VALUES(?, ?, ?))";

@Override
    public Project selectProject(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        Project project;
        try {
            connection = JdbcConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            project = createProject(resultSet);
        } finally {
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
        return project;
    }

    @Override
    public List <Project> selectAll () throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List <Project> result = new ArrayList <>();
        try {
            connection = JdbcConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                result.add(createProject(resultSet));
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

    public void delete(Long id) throws SQLException {
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

    public void insert(Project object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setDouble(2, object.getCost());
            preparedStatement.setDate(3, object.getDate());
        }finally{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }

    public void updateProject (Project object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
         connection = JdbcConnectionUtil.getConnection();
        connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setDouble(2, object.getCost());
        preparedStatement.setDate(3, object.getDate());
        preparedStatement.setLong(4, object.getId());
        }finally{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        return new Project(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("cost"),
                resultSet.getDate("date"));
    }
}
