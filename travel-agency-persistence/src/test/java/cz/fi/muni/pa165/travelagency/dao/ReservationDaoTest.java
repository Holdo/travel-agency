package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import cz.fi.muni.pa165.travelagency.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Diana Vilkolakova
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class ReservationDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerDao customerDao;
    
    @Autowired
    private ReservationDao reservationDao;
    
    @Autowired
    private TripDao tripDao;

    private Reservation reservation;
    private Customer customer;
    private Trip trip;
    private BigDecimal price = new BigDecimal(5000);

    @BeforeMethod
    public void createReservations() {
        reservation = new Reservation();
        customer = new Customer();

        String date = "2016-11-01";
        Date dateFrom = Date.valueOf(date);
        date = "2016-11-14";
        Date dateTo = Date.valueOf(date);
        date = "2016-12-05";
        Date dateFrom2 = Date.valueOf(date);
        date = "2016-12-17";
        Date dateTo2 = Date.valueOf(date);
        trip = new Trip();
        trip.setDateFrom(dateFrom);
        trip.setDateTo(dateTo);
        trip.setDestination("Praha");
        trip.setNumberOfAvailable(5);
        trip.setPrice(BigDecimal.valueOf(5000));
        
        tripDao.create(trip);

        customer.setEmail("diana@email.com");
        customer.setFirstName("Diana");
        customer.setLastName("Vilko");
        customer.setPassword("password");
        customer.setUsername("dada");
        customer.setRole(UserRole.ROLE_USER.toString());
        
        customerDao.create(customer);

        reservation.setCustomer(customer);
        reservation.setPrice(price);
        reservation.setTrip(trip);

        reservationDao.create(reservation);
    }

    @Test
    public void reservationAttributes() {
        Reservation res = em.find(Reservation.class, reservation.getId());
        Assert.assertEquals(res.getPrice(), price);
        Customer reservationCustomer = res.getCustomer();
        Assert.assertNotNull(res.getTrip());
        Assert.assertEquals(res.getTrip(), trip, "Trip is not set right in database");
        Assert.assertNotNull(reservationCustomer);
        Assert.assertEquals(reservationCustomer.getFirstName(), "Diana");
        Assert.assertEquals(reservationCustomer.getLastName(), "Vilko");
        Assert.assertEquals(reservationCustomer.getEmail(), "diana@email.com");
        Assert.assertEquals(reservationCustomer.getPassword(), "password");
        Assert.assertEquals(reservationCustomer.getUsername(), "dada");
    }

    @Test
    public void reservationCreation() {
        Reservation res = em.find(Reservation.class, reservation.getId());
        Assert.assertNotNull(res, "Reservation was not found in the database.");
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void reservationPriceNotNull() {
        Reservation res = new Reservation();
        res.setCustomer(customer);
        res.setPrice(null);
        res.setTrip(trip);
        reservationDao.create(res);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void reservationPriceNotNegative() {
        Reservation res = new Reservation();
        res.setCustomer(customer);
        reservation.setPrice(BigDecimal.valueOf(-1));
        res.setTrip(trip);
        reservationDao.create(res);
    }
    
    @Test
    public void findAllNotNull() {
        List<Reservation> foundReservations = reservationDao.getAll();
        Assert.assertNotNull(foundReservations);
    }

    @Test
    public void findAllTest() {
        List<Reservation> foundReservations = reservationDao.getAll();
        Assert.assertEquals(foundReservations.size(), 1, "There's an incorrect amount of reservations in the database!");
        Assert.assertTrue(foundReservations.contains(reservation), "Can't find list of reservations in the DB.");
    }
    
    @Test
    public void create() {
        reservationDao.create(reservation);
        Reservation foundReservation = em.find(Reservation.class, reservation.getId());
        Assert.assertNotNull(foundReservation);
    }

    @Test
    public void update() {
        reservation.setPrice(BigDecimal.valueOf(200));
        reservationDao.update(reservation);
        Reservation foundReservation = em.find(Reservation.class, reservation.getId());
        Assert.assertEquals(foundReservation.getPrice(), BigDecimal.valueOf(200));
    }
    
    @Test
    public void remove() {
        reservationDao.delete(reservation);
        Reservation foundReservation = em.find(Reservation.class, reservation.getId());
        Assert.assertNull(foundReservation);
    }
    
    @Test
    public void getReservationsForCustomer() {
        Assert.assertNotNull(customer.getId());
        Assert.assertTrue(reservationDao.getReservations(customer).size() == 1);
    }
}