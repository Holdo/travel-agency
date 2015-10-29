package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.Excursion;

/**
* 
*
* @author Julius Stassik
*/
public interface ExcursionDao {
    
	void create (Excursion excursion);
	
	void delete (Excursion excursion);
	
	void update (Excursion excursion);
		
	Excursion findById(Long id);
		
	List<Excursion> findAll();
		
}

    

