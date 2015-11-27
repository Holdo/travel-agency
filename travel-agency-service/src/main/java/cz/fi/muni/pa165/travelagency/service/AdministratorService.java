
package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Administrator;
import java.util.List;

/**
 *
 * @author Branislav Bohumel
 */
public interface AdministratorService {
    
    
    /*
     * Creates an administrator 
     * 
     * @param administrator to be created
     */
    void create(Administrator administrator);
    /*
     * Deletes an administrator
     * 
     * @param administrator to be deleted
     */
    void update(Administrator administrator);
    
    /*
     * Updates an administrator
     * 
     * @param administrator to be updated
     */
    void delete(Administrator administrator);
    
    /*
     * Finds and returns an administrator based on recieved id.
     *
     * @param administrator to be found 
     * @return Administrator object
     *         null if not found
     */
    Administrator findById(Long id);
    
    /*
     * Returns all the administrators.
     * 
     * @return List of all the administrators
     *         empty List if the are no administrators
     */
    List<Administrator> listAll();
    
}