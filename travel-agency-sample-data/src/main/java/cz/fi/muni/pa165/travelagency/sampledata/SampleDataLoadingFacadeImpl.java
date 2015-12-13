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
        administrator("admin", "admin@admin.com", "Adminislav", "Adminovič", "admin");
        Customer customer1 = customer("customer", "customer@customer.com", "Nákuposlav", "Nákupník", "customer");
        Customer customer2 = customer("customer2", "customer2@customer.com", "Nákuposlav2", "Nákupník2", "customer");
        Customer customer3 = customer("customer3", "customer3@customer.com", "Nákuposlav3", "Nákupník3", "customer");
        Customer customer4 = customer("customer4", "customer4@customer.com", "Nákuposlav4", "Nákupník4", "customer");
        Customer customer5 = customer("customer5", "customer5@customer.com", "Nákuposlav5", "Nákupník5", "customer");
        Trip trip1 = trip(Date.valueOf("2016-02-01"), Date.valueOf("2016-03-01"), "Florida, USA", 10, BigDecimal.valueOf(19520));
        Trip trip2 = trip(Date.valueOf("2016-03-04"), Date.valueOf("2016-04-04"), "Moscow, Russia", 8, BigDecimal.valueOf(7230.50));
        Trip trip3 = trip(Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50));
        Trip trip4 = trip(Date.valueOf("2016-05-10"), Date.valueOf("2016-06-10"), "Barcelona, Spain", 12, BigDecimal.valueOf(4500));
        Trip trip5 = trip(Date.valueOf("2016-06-13"), Date.valueOf("2016-07-13"), "Crete, Greece", 5, BigDecimal.valueOf(7900.90));
        Trip trip6 = trip(Date.valueOf("2016-07-16"), Date.valueOf("2016-08-16"), "Tokyo, Japan", 6, BigDecimal.valueOf(27980));
        Trip trip7 = trip(Date.valueOf("2016-08-19"), Date.valueOf("2016-09-19"), "Sydney, Australia", 10, BigDecimal.valueOf(29999.90));
        Trip trip8 = trip(Date.valueOf("2016-09-22"), Date.valueOf("2016-10-22"), "Malé, Maldives", 11, BigDecimal.valueOf(48500));
        Trip trip9 = trip(Date.valueOf("2016-10-25"), Date.valueOf("2016-11-26"), "George Town, Cayman Islands", 25, BigDecimal.valueOf(42399.90));
        trip2.addExcursion(excursion(Date.valueOf("2016-03-15"), "Lenin's Mausoleum", Duration.ofHours(3), BigDecimal.valueOf(1000), trip2));
        trip3.addExcursion(excursion(Date.valueOf("2016-04-12"), "Red Sea Scuba Diving", Duration.ofHours(7), BigDecimal.valueOf(1500), trip3));
        trip4.addExcursion(excursion(Date.valueOf("2016-05-18"), "Sagrada Família", Duration.ofHours(4), BigDecimal.valueOf(1200), trip4));
        trip6.addExcursion(excursion(Date.valueOf("2016-08-10"), "Mount Fuji", Duration.ofHours(10), BigDecimal.valueOf(3500), trip6));
        trip6.addExcursion(excursion(Date.valueOf("2016-07-27"), "Kamakura, Yokohama and Tokyo Bay", Duration.ofHours(12), BigDecimal.valueOf(3000), trip6));
        trip9.addExcursion(excursion(Date.valueOf("2016-10-30"), "Manta Ray Snorkeling", Duration.ofHours(5), BigDecimal.valueOf(2300), trip9));
        tripService.update(trip2);
        tripService.update(trip3);
        tripService.update(trip4);
        tripService.update(trip6);
        tripService.update(trip6);
        tripService.update(trip9);
        reservation(customer1, trip1);
        reservation(customer1, trip2);
        reservation(customer2, trip6);
        reservation(customer3, trip4);
        reservation(customer4, trip3);
        reservation(customer4, trip1);
        reservation(customer4, trip6);
        reservation(customer5, trip5);
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
