package cz.fi.muni.pa165.travelagency.sampledata;

import cz.fi.muni.pa165.travelagency.entity.*;
import cz.fi.muni.pa165.travelagency.service.AdministratorService;
import cz.fi.muni.pa165.travelagency.service.CustomerService;
import cz.fi.muni.pa165.travelagency.service.ExcursionService;
import cz.fi.muni.pa165.travelagency.service.ReservationService;
import cz.fi.muni.pa165.travelagency.service.TripService;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    
    @Autowired
    AdministratorService administratorService;
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    ExcursionService excursionService;
    
    @Autowired
    ReservationService reservationService;

    @Autowired
    TripService tripService;
    
    @Override
    public void loadData() throws IOException {
        administrator("admin", "admin@admin.com", "Adminislav", "Adminovič", "$2a$04$rotbNApV5YhP5R/ziPJdgO2zp25lWfm93XVTKSuoppn1/IodxIEcC");
        customer("customer", "customer@customer.com", "Nákuposlav", "Nákupník", "$2a$04$kkgXePevBjclCnhbzcrQrOorQfwVPiGZH9mS7WYylADBdx/uggBsK");
    }
    
    protected Administrator administrator(String username, String email, String firstname, String lastname, String password) {
        Administrator administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setEmail(email);
        administrator.setFirstName(firstname);
        administrator.setLastName(lastname);
        administrator.setPassword(password);
        administratorService.create(administrator);
        return administrator;
    }
    
    protected Customer customer(String username, String email, String firstname, String lastname, String password) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setPassword(password);
        customerService.create(customer);
        return customer;
    }
    
    protected Excursion excursion(Date date, String destination, Duration duration, BigDecimal price, Trip trip) {
        Excursion excursion = new Excursion();
        excursion.setDate(date);
        excursion.setDestination(destination);
        excursion.setDuration(duration);
        excursion.setPrice(price);
        excursion.setTrip(trip);
        excursionService.create(excursion);
        return excursion;
    }
    
    protected long reservation(Customer customer, Trip trip) {
        return customerService.makeReservation(customer, trip);
    }
    
    protected Trip trip(Date dateFrom, Date dateTo, String destination, int numberOfAvailable, BigDecimal price) {
        Trip trip = new Trip();
        trip.setDateFrom(dateFrom);
        trip.setDateTo(dateTo);
        trip.setDestination(destination);
        trip.setNumberOfAvailable(numberOfAvailable);
        trip.setPrice(price);
        tripService.create(trip);
        return trip;
    }
    
}
