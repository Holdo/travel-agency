package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * 
 * @author Branislav Bohumel
 */
public class AdministratorDaoImpl extends UserDaoImpl implements AdministratorDao {
    
    @PersistenceContext
    private EntityManager em;

    //Functions returning lists of customers and reservations
    @Override
    public List<Customer> listCustomers() {
	TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
	return query.getResultList();
    }
 
    @Override
    public List<Reservation> listReservations() {
	TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r", Reservation.class);
	return query.getResultList();
    }
    
    //Following are CRUD operations for Administrator concerning Trip class
    @Override
    public void createTrip(Trip trip) {
        em.persist(trip);
    }  
    
    @Override
    public Trip readTrip(Long id) {
        return em.find(Trip.class, id);
    }  
    
    @Override
    public void updateTrip(Long oldTripId, Trip newTrip) {
        Trip oldTrip = em.find(Trip.class, oldTripId);
        em.remove(oldTrip);
        em.persist(newTrip);
    } 
    
    @Override
    public void deleteTrip(Trip trip) {
        em.remove(trip);
    }
    
    //Following are CRUD operations for Administrator concerning Excursion class    
    @Override
    public void createExcursion(Excursion excursion) {
        em.persist(excursion);
    }
    
    @Override
    public Excursion readExcursion(Long id) {
        return em.find(Excursion.class, id);
    }  
    
    @Override
    public void updateExcursion(Long oldExcursionId, Excursion newExcursion) {
        Excursion oldExcursion = em.find(Excursion.class, oldExcursionId);
        em.remove(oldExcursion);
        em.persist(newExcursion);
    } 
    
    @Override
    public void deleteExcursion(Excursion excursion) {
        em.remove(excursion);
    }
    
}
