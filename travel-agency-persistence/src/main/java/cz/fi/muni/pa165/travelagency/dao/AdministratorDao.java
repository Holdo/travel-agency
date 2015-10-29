package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;

/**
 *
 * 
 * @author Branislav Bohumel
 */
public interface AdministratorDao {
    public List<Customer> listCustomers();
    public List<Reservation> listReservations();
    public void createTrip(Trip trip);
    public Trip readTrip(Long id);
    public void updateTrip(Long oldTripId, Trip newTrip);
    public void deleteTrip(Trip trip);
    public void createExcursion(Excursion excursion);
    public Excursion readExcursion(Long id);
    public void updateExcursion(Long oldExcursionId, Excursion newExcursion); 
    public void deleteExcursion(Excursion excursion);
    
}
