package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
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

    private Reservation reservation;
    private Customer customer;
    private Trip trip1;
    private Trip trip2;
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
        trip1 = new Trip();
        trip2 = new Trip();
        trip1.setDateFrom(dateFrom);
        trip1.setDateTo(dateTo);
        trip1.setDestination("Praha");
        trip1.setNumberOfAvailable(5);
        trip1.setPrice(BigDecimal.valueOf(5000));
        trip2.setDateFrom(dateFrom2);
        trip2.setDateTo(dateTo2);
        trip2.setDestination("Bratislava");
        trip2.setNumberOfAvailable(7);
        trip2.setPrice(BigDecimal.valueOf(3000));

        customer.setEmail("diana@email.com");
        customer.setFirstName("Diana");
        customer.setLastName("Vilko");
        customer.setPassword("password");
        customer.setUsername("dada");
        
        customerDao.create(customer);

        reservation.setCustomer(customer);
        reservation.setPrice(price);
        reservation.addTrip(trip1);
        reservation.addTrip(trip2);

        reservationDao.create(reservation);
    }

    @Test
    public void reservationAttributes() {
        Reservation res = em.find(Reservation.class, reservation.getId());
        Assert.assertEquals(res.getPrice(), price);
        Customer reservationCustomer = res.getCustomer();
        Assert.assertNotNull(res.getTrips());
        Assert.assertTrue(res.getTrips().contains(trip1), "Trip not set right in database");
        Assert.assertTrue(res.getTrips().contains(trip2), "Trip not set right in database");
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
        res.addTrip(trip1);
        res.addTrip(trip2);
        reservationDao.create(res);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void reservationPriceNotNegative() {
        Reservation res = new Reservation();
        res.setCustomer(customer);
        reservation.setPrice(BigDecimal.valueOf(-1));
        res.addTrip(trip1);
        res.addTrip(trip2);
        reservationDao.create(res);
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
}