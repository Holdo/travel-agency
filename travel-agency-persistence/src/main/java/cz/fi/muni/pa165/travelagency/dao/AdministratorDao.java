package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Administrator;
import java.util.List;

/**
 * Interface for administrator. Administrator extends user
 * 
 * @author Branislav Bohumel
 */
public interface AdministratorDao {
    /*
     * Creates an administrator entity in the database.
     * 
     * @param administrator to be created in the database, 
     *        must not be null
     */
    public void create(Administrator administrator);
    
    /*
     * Finds and returns an administrator entity from the database, 
     * based on recieved id.
     *
     * @param administrator to be found in the database, 
     *        must not be null
     * @return Trip object.
     *         null if not found
     */
    public Administrator getById(Long id);
    
    /*
     * Updates an administrator entity in the database.
     * 
     * @param administrator to be updated in the database, 
     *        must not be null
     */
    public void update(Administrator administrator);
    
    /*
     * Deletes an administrator entity in the database.
     * 
     * @param administrator to be deleted from the database, 
     *        must not be null
     */
    public void delete(Administrator administrator);
    
    /*
     * Returns all the administrators in the database.
     * 
     * @return List of all the administrators in the database
     *         empty List if the are no administrators in the database
     */
    public List<Administrator> getAll();
    
}
