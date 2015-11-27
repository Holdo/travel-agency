package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * TripServiceImpl test
 * 
 * @author Julius Stassik
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class TripServiceTest extends AbstractJUnit4SpringContextTests {
    
    @Mock
    private TripDao tripDao;
    
    @Autowired
    @InjectMocks
    private TripService tripService;
    
    private Trip trip1;
    private Trip trip2;
    
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
        trip1.setDestination("Kreta");
        trip1.setNumberOfAvailable(8);
        trip1.setPrice(new BigDecimal("500"));
        
        trip2 = new Trip();
        trip2.setDateFrom(Date.valueOf(dateFrom2));
        trip2.setDateTo(Date.valueOf(dateTo2));
        trip2.setDestination("Ateny");
        trip2.setNumberOfAvailable(10);
        trip2.setPrice(new BigDecimal("1500"));
        
    }
    
    @Test
    public void createTripTest(){
        doNothing().when(tripDao).create(any(Trip.class));
        
        tripDao.create(trip1);
        verify(tripDao).create(trip1);        
    }
    
    
    @Test
    public void updateTripTest(){
        doNothing().when(tripDao).update(any(Trip.class));
        
        trip1.setDestination("Madrid");
        tripDao.update(trip1);
        verify(tripDao).update(trip1);        
    }
    
    @Test
    public void deleteTripTest(){
        doNothing().when(tripDao).delete(any(Trip.class));
        
        tripDao.delete(trip1);
        verify(tripDao).delete(trip1);        
    }
    
    @Test
    public void getTripByIdTest(){
        Long id = trip1.getId();
        when(tripDao.findById(any(Long.class))).thenReturn(trip1);
        
        tripDao.findById(id);
        verify(tripDao).findById(id);
    }
    
    @Test
    public void getAllTripsTest(){
        List<Trip> trips = new ArrayList();
        trips.add(trip1);
        trips.add(trip2);
        
        when(tripDao.findAll()).thenReturn(trips);
        List<Trip> tripsDto = new ArrayList();
        tripsDto = tripDao.findAll();
        assertEquals(tripsDto.size(), 2);
        verify(tripDao).findAll();
    }
}
