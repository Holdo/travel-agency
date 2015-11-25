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
   public Trip createTrip(Trip trip);
   
   /**
    * update trip
    * 
    * @param trip which will be updated
    */
   public void updateTrip(Trip trip);
   
   /**
    * delete trip
    * 
    * @param trip which will be deleted 
    */
   public void deleteTrip(Trip trip);
   
   /**
    * find trip by its id
    * 
    * @param id - id of trip which has to be found
    * @return trip which was found 
    */
   public Trip findById(Long id);
   
   /**
    * find all trips
    * 
    * @return list of all trips
    */
   public List<Trip> findAll();
    
}
