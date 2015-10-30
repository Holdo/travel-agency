package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 * @author Diana Vilkolakova
 */
@Repository
public class TripDaoImpl implements TripDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Trip trip) {
        em.persist(trip);
    }

    @Override
    public void update(Trip trip) {
        em.merge(trip);
    }

    @Override
    public void delete(Trip trip) {
        em.remove(findById(trip.getId()));
    }

    @Override
    public Trip findById(Long id) {
        return em.find(Trip.class, id);
    }

    @Override
    public List<Trip> findAll() {
        return em.createQuery("SELECT t FROM Trip t", Trip.class).getResultList();
    }
}