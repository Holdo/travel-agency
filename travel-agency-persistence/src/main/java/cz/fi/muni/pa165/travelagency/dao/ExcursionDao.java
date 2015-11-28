package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.Excursion;

/**
* 
*
* @author Julius Stassik
*/
public interface ExcursionDao {
    
	/*
	 * Create a excursion entity in the database.
	 *
	 * @param excursion to be created, must not be null
	 */
	void create (Excursion excursion);
	
	/*
	 * Remove a excursion entity from the database.
	 *
	 * @param excursion to be deleted, must not be null 
	 *	and must be in managed state
	 */
	void delete (Excursion excursion);
	
	/*
	 * Update a excursion entity in the database.
	 *
	 * @param excursion to be updated, must not be null 
	 */
	void update (Excursion excursion);
		
	/*
	 * Find a excursion in the database by its id.
	 *
	 * @param excursion id, must not be null
	 * @return Excursion object or null, if not found
	 */
	Excursion getById(Long id);
		
	/*
	 * Lists all the excursions from the database.
	 *
	 * @return List of all the excursions from the database
	 * List is empty if the are no excursions in the database
	 */
	List<Excursion> getAll();
		
}

    
