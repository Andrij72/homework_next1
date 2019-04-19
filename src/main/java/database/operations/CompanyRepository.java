package database.operations;

import database.data.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    void insertCompany (Company company)throws SQLException;

    boolean updateCompany (Company company) throws SQLException;

    Company selectByIdCompany (Long id) throws SQLException;

     List <Company> selectAll () throws SQLException;

     void deleteCompany (Long id) throws SQLException;
}
