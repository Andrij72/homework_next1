package com.database.operations;

import com.database.data.DBCloseConnect;
import com.database.data.JdbcConnectionUtil;
import com.database.data.Skill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillCrud {

    private static final String QUERY_SELECT = "SELECT * FROM skills";
    private static final String SELECT_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String INSERT = "INSERT INTO developers(industry, level) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE developers SET industry = ?, level = ? WHERE id = ?";

    public Skill selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Skill skill = createSkill(resultSet);

        DBCloseConnect.close(resultSet, connection, preparedStatement);
        return skill;
    }

    public List<Skill> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_SELECT);
        List<Skill> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createSkill(resultSet));
        }

        DBCloseConnect.close(resultSet, connection, statement);
        return result;
    }

    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, id);

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void insert(Skill object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getIndustry());
        preparedStatement.setString(2, object.getLevel());

        DBCloseConnect.close(connection, preparedStatement);
    }

    public void update(Skill object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getIndustry());
        preparedStatement.setString(2, object.getLevel());
        preparedStatement.setInt(3, object.getId());

        DBCloseConnect.close(connection, preparedStatement);
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        return new Skill(resultSet.getInt("id"),
                resultSet.getString("industry"),
                resultSet.getString("level"));
    }
}
