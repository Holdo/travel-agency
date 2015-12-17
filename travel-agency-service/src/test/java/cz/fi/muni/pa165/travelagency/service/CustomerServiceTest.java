package cz.fi.muni.pa165.travelagency.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDaoImpl;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;

import cz.fi.muni.pa165.travelagency.exceptions.TravelAgencyServiceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //this is here so the mockito does not affect other tests
public class CustomerServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private ReservationDao reservationDao;

    @Autowired
    private ReservationDao reservationDaoUnmocked;

    @Mock
    private CustomerDao customerDao;

    @Mock
    private TripDao tripDao;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Customer customer;
    private Excursion excursion;
    private Trip trip;
    
    @BeforeMethod
    public void prepareTestEntities(){
        reservationDao = Mockito.mock(ReservationDaoImpl.class);

        customer = new Customer();
        customer.setEmail("customer@test.com");
        customer.setFirstName("David");
        customer.setLastName("Hasselhoff");
        customer.setPassword("password");
        customer.setUsername("Hoff");
        customer.setRole(UserRole.ROLE_USER.toString());

        excursion = new Excursion();
        excursion.setDate(Date.valueOf("2015-01-03"));
        excursion.setDestination("Some nice islands.");
        excursion.setDuration(Duration.ofHours(8));
        excursion.setPrice(BigDecimal.valueOf(1250.50));

        trip = new Trip();
        trip.setDateFrom(Date.valueOf("2015-01-02"));
        trip.setDateTo(Date.valueOf("2015-05-06"));
        trip.setDestination("Zemplinska Sirava");
        trip.setNumberOfAvailable(3);
        trip.setPrice(BigDecimal.valueOf(12000.50));

        excursion.setTrip(trip);
        trip.addExcursion(excursion);
    }

    @Test
    public void makeReservationReducesNumberOfAvailableTrips() {
        doNothing().when(customerDao).update(any(Customer.class));
        doNothing().when(tripDao).update(any(Trip.class));
        when(reservationDao.create(any(Reservation.class))).thenReturn(15L);
        customerService.makeReservation(customer, trip);
        Assert.assertEquals(trip.getNumberOfAvailable(), Integer.decode("2"));
    }

    @Test(expectedExceptions = TravelAgencyServiceException.class)
    public void makeReservationFailsIfNoTripsAreAvailable() {
        trip.setNumberOfAvailable(0);
        customerService.makeReservation(customer, trip);
    }

    @Test
    public void makeReservationFinalPriceTest() {
        when(reservationDao.create(any(Reservation.class))).thenCallRealMethod();
        Long reservationId = customerService.makeReservation(customer, trip);
        System.out.println("reservationId = " + reservationId);
        Reservation newReservation = reservationDaoUnmocked.getById(reservationId);
        Assert.assertEquals(newReservation.getPrice(), trip.getPrice().add(excursion.getPrice()));
    }
}