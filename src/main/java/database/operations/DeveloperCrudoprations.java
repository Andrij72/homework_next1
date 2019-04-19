package database.operations;

import database.data.Company;
import database.data.Customer;
import database.data.Developer;

import java.sql.SQLException;
import java.util.List;

public interface DeveloperCrudoprations {

    boolean insertDeveloper (Developer developer) throws SQLException;

    void updateDeveloper (Developer developer) throws SQLException;

    List <Company> selectAll () throws SQLException;

    void deleteDeveloper (Long id) throws SQLException;
}