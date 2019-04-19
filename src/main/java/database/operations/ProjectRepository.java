package database.operations;

import database.data.Developer;
import database.data.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {

    Project selectProject (Long id) throws SQLException;

    List<Project> selectAll () throws SQLException;

     void delete(Long id) throws SQLException;

    void updateProject (Project object) throws SQLException;

     void insert(Project object) throws SQLException;
}
