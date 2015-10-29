package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;
import cz.fi.muni.pa165.travelagency.entity.Reservation;

/**
 *
 * 
 * @author Julius Stassik
 */
public interface ReservationDao {
	
	void create (Reservation reservation);
	
	void delete (Reservation reservation);
	
	void update (Reservation reservation);
	
	Reservation findById(Long id);
	
	List<Reservation> findAll();
    
}
