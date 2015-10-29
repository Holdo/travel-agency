package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.Trip;

/**
 *
 * 
 * @author 
 */
public interface TripDao {
	
	void create (Trip trip);
	
	void delete (Trip trip);
	
	void update (Trip trip);
	
	Trip findById(Long id);
	
	List<Trip> findAll();
    
}
