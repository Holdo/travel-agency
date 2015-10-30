package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import java.util.List;

/**
 *
 *
 * @author Michal Holic
 */
public interface CustomerDao {

    void create(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
}
