package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;

/**
 * Interface describing a reservation in the system.
 * 
 * @author Michal Holic
 */
public interface ReservationDao {
    
    /*
     * Creates a reservation entity in the database.
     * 
     * @param reservation to be created, must not be null
     */
    void create(Reservation reservation);
    
    /*
     * Updates a reservation entity in the database.
     * 
     * @param reservation to be updated, must not be null
     */
    void update(Reservation reservation);
    
    /*
     * Deletes a reservation entity in the database.
     * 
     * @param reservation to be deleted, must not be null and must be in managed state
     */
    void delete(Reservation reservation);
    
    /*
     * Finds a reservation in the database by it's id.
     * 
     * @param reservation id, must not be null
     * @return Reservation object or null, if not found
     */
    Reservation findById(Long id);
    
    /*
     * Lists all the reservations in the database.
     * 
     * @return List of all the reservations in the database
     *         List is empty if the are no reservations in the database
     */
    List<Reservation> findAll();
}
