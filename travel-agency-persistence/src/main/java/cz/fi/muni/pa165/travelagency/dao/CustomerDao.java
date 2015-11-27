package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import java.util.List;

/**
 * Interface describing a customer in the system.
 *
 * @author Michal Holic
 */
public interface CustomerDao {

    /*
     * Creates a customer entity in the database.
     * 
     * @param customer to be created, must not be null
     */
    void create(Customer customer);
    
    /*
     * Updates a customer entity in the database.
     * 
     * @param customer to be updated, must not be null
     */
    void update(Customer customer);
    
    /*
     * Deletes a customer entity in the database.
     * 
     * @param customer to be deleted, must not be null and must be in managed state
     */
    void delete(Customer customer);
    
    /*
     * Finds a customer in the database by his id.
     * 
     * @param customer id, must not be null
     * @return Customer object or null, if not found
     */
    Customer findById(Long id);
    
    /*
     * Finds a customer in the database by his email.
     * 
     * @param customer email, must not be null or empty
     * @return Customer object or null, if not found
     */
    Customer findByEmail(String email);
    
    /*
     * Lists all the customers in the database.
     * 
     * @return List of all the customers in the database
     *         List is empty if the are no customers in the database
     */
    List<Customer> findAll();
}
