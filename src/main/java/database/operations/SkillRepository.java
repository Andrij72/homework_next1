package database.operations;

import database.data.Skill;

import java.sql.SQLException;
import java.util.List;

public interface SkillRepository {

    Skill selectById (Long id) throws SQLException;

    List <Skill> selectAll () throws SQLException;

    void insertSkill (Skill object) throws SQLException;

    void updateSkill (Skill object) throws SQLException ;
}
