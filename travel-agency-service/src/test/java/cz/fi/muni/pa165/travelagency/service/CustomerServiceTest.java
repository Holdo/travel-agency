package cz.fi.muni.pa165.travelagency.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.Set;

import cz.fi.muni.pa165.travelagency.exceptions.TravelAgencyServiceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;

/**
 *
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class CustomerServiceTest extends AbstractTransactionalTestNGSpringContextTests {

	@Mock
	private ReservationDao reservationDao;

	@Mock
	private CustomerDao customerDao;

	@Mock
	private TripDao tripDao;

	@Mock
	private Reservation reservation;
    
    @Autowired
	@InjectMocks
    private CustomerService customerService;

	@Autowired
	private ExcursionService excursionService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private TripService tripService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Customer customer = new Customer();
	private Excursion excursion = new Excursion();
    private Trip trip = new Trip();
    
    @BeforeMethod
    public void prepareTestEntities(){
    	customer.setEmail("customer@test.com");
        customer.setFirstName("David");
        customer.setLastName("Hasselhoff");
        customer.setPassword("password");
        customer.setUsername("Hoff");


		excursion.setDate(Date.valueOf("2015-01-03"));
		excursion.setDestination("Some nice islands.");
		excursion.setDuration(Duration.ofHours(8));
		excursion.setPrice(BigDecimal.valueOf(1250.50));

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
		doNothing().when(reservationDao).create(any(Reservation.class));
		doNothing().when(tripDao).create(any(Trip.class));
		when(reservation.getId()).thenReturn(15l);
		//doReturn(Long.decode("15")).when(reservation).getId();
		customerService.makeReservation(customer, trip);
        Assert.assertEquals(trip.getNumberOfAvailable(), Integer.decode("2"));
    }

	@Test(expectedExceptions = TravelAgencyServiceException.class)
	public void makeReservationFailsIfNoTripsAreAvailable() {
		trip.setNumberOfAvailable(0);
		customerService.makeReservation(customer, trip);
	}
}
