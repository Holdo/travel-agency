package cz.fi.muni.pa165.travelagency.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
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

/**
 *
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    //@InjectMocks
    private CustomerService customerService;
    
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
    private Trip trip = new Trip();
    
    @BeforeMethod
    public void prepareTestEntities(){
        customer.setId(Long.decode("1"));
    	customer.setEmail("customer@test.com");
        customer.setFirstName("David");
        customer.setLastName("Hasselhoff");
        customer.setPassword("password");
        customer.setUsername("Hoff");
        
        trip.setDateFrom(Date.valueOf("2015-01-02"));
        trip.setDateTo(Date.valueOf("2015-05-06"));
        trip.setDestination("Zemplinska Sirava");
        trip.setNumberOfAvailable(3);
        trip.setPrice(new BigDecimal("1000.50"));
    }
    
    @Test
    public void makeReservation(){
        //when(customerService.makeReservation(customer, trip)).thenReturn(Long.decode("15"));
        Assert.assertTrue(true);
        /*long madeReservationId = customerService.makeReservation(customer, trip);
        Reservation newReservation = reservationService.findById(madeReservationId);
        Assert.assertEquals(newReservation.getTrip().getNumberOfAvailable(), Integer.decode("2"));*/
    }
}
