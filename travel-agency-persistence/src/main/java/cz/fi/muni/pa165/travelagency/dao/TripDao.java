package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;

/**
 * Interface for trips in the system. Trips contains dates and price and they
 * can be reserved by customer.
 *
 * @author Diana Vilkolakova
 */
public interface TripDao {

    /*
     * Creates a trip entity in the database.
     * 
     * @param trip to be created in the database, must not be null
     */
    void create(Trip trip);

    /*
     * Updates a trip entity in the database.
     * 
     * @param trip to be updated in the database, 
     *        must not be null
     */
    void update(Trip trip);

    /*
     * Deletes a trip entity in the database.
     * 
     * @param trip to be deleted  in the database, 
     *        must not be null
     *        must have properly set dates
     */
    void delete(Trip trip);

    /*
     * Finds a trip in the database with given id.
     * 
     * @param trip id, must not be null
     * @return Trip object.
     *         null if not found
     */
    Trip getById(Long id);

    /*
     * Returns all the trips in the database.
     * 
     * @return List of all the trips in the database
     *         empty List if the are no trips in the database
     */
    List<Trip> getAll();
}
