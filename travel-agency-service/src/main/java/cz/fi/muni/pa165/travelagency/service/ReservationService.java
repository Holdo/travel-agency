package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;

/**
 *
 * @author Michal Holic
 */
public interface ReservationService {
    
    public void create(Reservation reservation);
    public void update(Reservation reservation);
    public void delete(Reservation reservation);
    public Reservation findById(Long id);
    public List<Reservation> findAll();
}
