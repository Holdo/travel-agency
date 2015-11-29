package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julius Stassik
 */


public interface TripService {
    
    
   /**
    * create Trip
    * 
    * @param trip
    * @return created Trip
    */ 
   Trip create(Trip trip);
   
   /**
    * update trip
    * 
    * @param trip which will be updated
    */
   void update(Trip trip);
   
   /**
    * delete trip
    * 
    * @param trip which will be deleted 
    */
   void delete(Trip trip);
   
   /**
    * find trip by its id
    * 
    * @param id - id of trip which has to be found
    * @return trip which was found 
    */
   Trip getById(Long id);
   
   /**
    * find all trips
    * 
    * @return list of all trips
    */
   List<Trip> getAll();
    
}
