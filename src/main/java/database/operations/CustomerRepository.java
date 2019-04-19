package database.operations;

import database.data.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    boolean insertCustomer (Customer customer);

    boolean updateCustomer (Customer customer) throws SQLException;

    Customer selectByIdCustomer (Long id) throws SQLException;

    public List <Customer> selectAll () throws SQLException;

    void deleteCustomer (Long id) throws SQLException;
}
