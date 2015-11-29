package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import java.util.List;

/**
 *
 * @author Branislav Bohumel
 */
public interface TripFacade {
    
    /*
     * Creates a trip 
     * 
     * @param trip to be created
     */
    void create(TripDTO trip);
    /*
     * Deletes a trip
     * 
     * @param trip to be deleted
     */
    void delete(Long id);
    
    /*
     * Updates a trip
     * 
     * @param trip to be updated
     */
    void update(TripDTO trip);
    
    /*
     * Finds and returns a trip based on recieved id.
     *
     * @param trip to be found 
     * @return Trip object
     *         null if not found
     */
    TripDTO getById(Long id);
    
    /*
     * Returns all the trips.
     * 
     * @return List of all the trips
     *         empty List if the are no trips
     */
    List<TripDTO> getAll();
}