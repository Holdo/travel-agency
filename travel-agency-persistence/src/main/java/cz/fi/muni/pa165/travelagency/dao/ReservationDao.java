package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;
import cz.fi.muni.pa165.travelagency.entity.Reservation;

/**
 *
 * 
 * @author Julius Stassik
 */
public interface ReservationDao {
	
	/*
	 * Create a reservation entity in the database.
	 *
	 * @param reservation to be created, must not be null
	 */
	void create (Reservation reservation);
	
	/*
	 * Remove a reservation entity from the database.
	 *
	 * @param reservation to be deleted, must not be null 
	 *	and must be in managed state
	 */
	void delete (Reservation reservation);
	
	/*
	 * Update a reservation entity in the database.
	 *
	 * @param reservation to be updated, must not be null 
	 */
	void update (Reservation reservation);
	
	/*
	 * Find a reservation in the database by its id.
	 *
	 * @param reservation id, must not be null
	 * @return Reservation object or null, if not found
	 */
	Reservation findById(Long id);
	
	/*
	 * Lists all the reservations from the database.
	 *
	 * @return List of all the reservations from the database
	 * List is empty if the are no reservations in the database
	 */
	List<Reservation> findAll();
    
}
