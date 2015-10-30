package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;

/**
 *
 * 
 * @author Diana Vilkolakova
 */
public interface TripDao {
    void create(Trip trip);
    void update(Trip trip);
    void delete(Trip trip);
    Trip findById(Long id);
    List<Trip> findAll();
}