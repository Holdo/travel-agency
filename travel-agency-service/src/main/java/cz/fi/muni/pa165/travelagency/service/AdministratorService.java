package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;

/**
 *
 * @author Branislav Bohumel
 */
public interface AdministratorService {
    
    List<Customer> listCustomers();
    List<Reservation> listReservations();
    
    void createTrip(Trip trip);
    void updateTrip(Trip trip);
    void deleteTrip(Trip trip);
    Trip findTripById(Long id);
    List<Trip> listTrips();
            
    void createExcursion(Excursion excursion);
    void updateExcursion(Excursion excursion);
    void deleteExcursion(Excursion excursion);
    Excursion findExcursionById(Long id);
    List<Excursion> listExcursions();
    
}
