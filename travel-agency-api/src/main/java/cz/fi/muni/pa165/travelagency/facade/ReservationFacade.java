package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import java.util.List;

/**
 *
 * @author Julius Stassik
 */
public interface ReservationFacade {
    /**
     * Create reservation
     * 
     * @param reservation - reservation which will be created 
     */
    public void create(ReservationDTO reservation);
    
    /**
     * Delete reservation specified by its id
     * 
     * @param id - id of deleted reservation
     */
    public void delete(Long id);
    
    /**
     * Update reservation
     * 
     * @param reservation - reservation which will be updated
     */
    public void update(ReservationDTO reservation);
    
    /**
     * Find reservation by its id
     * 
     * @param id - id of reservation which will be found
     * @return ReservationDTO - desired reservation or null when there is no 
     *                         reservation with specified id
     */
    public ReservationDTO getById(Long id);
    
    /**
     * find all reservations
     * 
     * @return list of all reservations
     */
    public List<ReservationDTO> getAll();
}
