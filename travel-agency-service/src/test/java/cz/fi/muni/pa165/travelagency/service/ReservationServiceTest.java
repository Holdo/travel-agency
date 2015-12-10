package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import cz.fi.muni.pa165.travelagency.entity.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * ReservationServiceImpl test
 * 
 *
 * @author Julius Stassik
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class ReservationServiceTest extends AbstractJUnit4SpringContextTests {
    
    @Mock
    private ReservationDao reservationDao;
    
    @Mock
    private CustomerDao customerDao;
    
    @Mock
    private TripDao tripDao;
    
    @Autowired
    @InjectMocks
    private ReservationService reservationService;
    
    private Reservation reservation1;
    private Reservation reservation2;
    
    private Trip trip1;
    private Trip trip2;
    
    private Customer customer1;
    private Customer customer2;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        
        String dateFrom1 = "2015-12-01";  
	String dateFrom2 = "2015-12-02"; 
	String dateTo1 = "2015-12-24";  
	String dateTo2 = "2015-12-31"; 
        
        trip1 = new Trip();
        trip1.setDateFrom(Date.valueOf(dateFrom1));
        trip1.setDateTo(Date.valueOf(dateTo1));
        trip1.setDestination("Lunik IX.");
        trip1.setNumberOfAvailable(8);
        trip1.setPrice(new BigDecimal("500"));
        
        trip2 = new Trip();
        trip2.setDateFrom(Date.valueOf(dateFrom2));
        trip2.setDateTo(Date.valueOf(dateTo2));
        trip2.setDestination("Monako");
        trip2.setNumberOfAvailable(10);
        trip2.setPrice(new BigDecimal("1500"));
        
        customer1 = new Customer();
        customer1.setEmail("tucniak@tulen.eu");
        customer1.setFirstName("Vlado");
        customer1.setLastName("Pokorny");
        customer1.setPassword("123456");
        customer1.setUsername("vladko");
        customer1.setRole(UserRole.ROLE_USER);
        
        customer2 = new Customer();
        customer2.setEmail("velky@maly.sk");
        customer2.setFirstName("Juraj");
        customer2.setLastName("Janosik");
        customer2.setPassword("654321");
        customer2.setUsername("bohaty");
        customer2.setRole(UserRole.ROLE_USER);
        
        
        reservation1 = new Reservation();
        reservation1.setCustomer(customer1);
        reservation1.setTrip(trip1);
        reservation1.setPrice(new BigDecimal("600"));
        
        reservation2 = new Reservation();
        reservation2.setCustomer(customer2);
        reservation2.setTrip(trip2);
        reservation2.setPrice(new BigDecimal("1600"));
                                        
    }
    
    @Test
    public void createReservationTest(){
        doNothing().when(reservationDao).update(any(Reservation.class));
        
        reservationService.create(reservation1);
        verify(reservationDao).create(reservation1);
    }
    
    @Test
    public void updateReservationTest(){
        doNothing().when(reservationDao).update(any(Reservation.class));
        
        reservation1.setPrice(new BigDecimal("1000000"));
        reservationService.update(reservation1);
        verify(reservationDao).update(reservation1);
    }
    
    @Test
    public void deleteReservationTest(){
        doNothing().when(reservationDao).delete(any(Reservation.class));
        
        reservationService.delete(reservation1);
        verify(reservationDao).delete(reservation1);        
    }
    
    @Test
    public void findByIdTest(){
        Long id = reservation1.getId();
        when(reservationDao.getById(any(Long.class))).thenReturn(reservation1);
        
        reservationService.getById(id);
        verify(reservationDao).getById(id);
    }
    
    @Test
    public void findAllTest(){
        List<Reservation> reservations = new ArrayList<>();        
        reservations.add(reservation1);
        reservations.add(reservation2);
        
        when(reservationDao.getAll()).thenReturn(reservations);
        List<Reservation> reservationsDto = reservationService.getAll();
        assertEquals(reservationsDto.size(), 2);
        verify(reservationDao).getAll();
    }
            
}
