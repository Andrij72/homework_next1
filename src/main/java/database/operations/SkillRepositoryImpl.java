package database.operations;
import database.data.JdbcConnectionUtil;
import database.data.Skill;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository{
    private static final String QUERY_SELECT = "SELECT * FROM skills";
    private static final String SELECT_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String INSERT = "INSERT INTO developers(industry, level) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE developers SET industry = ?, level = ? WHERE id = ?";

    @Override
    public Skill selectById (Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Skill skill;
        try {
            connection = JdbcConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            skill = createSkill(resultSet);
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
        return skill;
    }

    @Override
    public List <Skill> selectAll () throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List <Skill> result = new ArrayList <>();
        try {
            connection = JdbcConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QUERY_SELECT);
            while (resultSet.next()) {
                result.add(createSkill(resultSet));
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

    public void deleteById (Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_DELETE);
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

    public void insertSkill (Skill object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getIndustry());
            preparedStatement.setString(2, object.getLevel());
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
    public void updateSkill (Skill object) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getIndustry());
            preparedStatement.setString(2, object.getLevel());
            preparedStatement.setLong(3, object.getId());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Skill createSkill (ResultSet resultSet) throws SQLException {
        return new Skill(resultSet.getLong("id"),
                resultSet.getString("industry"),
                resultSet.getString("level"));
    }
}
