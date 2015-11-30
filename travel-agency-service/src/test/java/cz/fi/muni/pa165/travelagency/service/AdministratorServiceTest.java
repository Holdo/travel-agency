package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.AdministratorDao;
import cz.fi.muni.pa165.travelagency.entity.Administrator;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Trip;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Branislav Bohumel
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class AdministratorServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private AdministratorDao administratorDao;
    
    @Autowired
    @InjectMocks
    private AdministratorService administratorService;
        
    @Autowired
    private TripService tripService;
    
    private Administrator administrator, administrator2;
    private List<Administrator> administratorList = new ArrayList<>(); 
    
    private Trip trip, tripUnavailable, tripExcursionless;
    private Excursion excursion;
    
    String date1 = "1999-09-27";  
    String date2 = "1999-10-11";    
    
    @BeforeMethod
    public void setup(){
        MockitoAnnotations.initMocks(this);
        
        administrator = new Administrator();
        administrator.setFirstName("Zly");
        administrator.setLastName("Policajt");
        administrator.setUsername("EvilAdmin");
        administrator.setPassword("IOwnYou");
        administrator.setEmail("Evil@Admin.org");       
        
        administrator2 = new Administrator();
        administrator2.setFirstName("Dobry");
        administrator2.setLastName("Policajt");
        administrator2.setUsername("GoodAdmin");
        administrator2.setPassword("IServeYou");
        administrator2.setEmail("Good@Admin.org");  
        
        
        trip = new Trip();
        trip.setDateFrom(Date.valueOf(date1));
        trip.setDateTo(Date.valueOf(date2));
        trip.setDestination("Alpy");
        trip.setNumberOfAvailable(8);
        trip.setPrice(new BigDecimal("10"));   
        tripService.create(trip);        
        excursion = new Excursion();
        excursion.setDate(Date.valueOf(date1));
        excursion.setDuration(Duration.ofHours(1));
        excursion.setDestination("Benzinka");
        excursion.setPrice(new BigDecimal("1000000"));
        excursion.setTrip(trip);
        trip.addExcursion(excursion);
        
        tripExcursionless = new Trip();
        tripExcursionless.setDateFrom(Date.valueOf(date1));
        tripExcursionless.setDateTo(Date.valueOf(date2));
        tripExcursionless.setDestination("Sicilia");
        tripExcursionless.setNumberOfAvailable(8);
        tripExcursionless.setPrice(new BigDecimal("50")); 
        
        tripUnavailable = new Trip();
        tripUnavailable.setDateFrom(Date.valueOf(date1));
        tripUnavailable.setDateTo(Date.valueOf(date2));
        tripUnavailable.setDestination("Florencia");
        tripUnavailable.setNumberOfAvailable(0);
        tripUnavailable.setPrice(new BigDecimal("20"));                       
    }
    
    @Test
    public void createTest(){     
        doNothing().when(administratorDao).create(any(Administrator.class));        
        administratorService.create(administrator);
        verify(administratorDao).create(administrator); 
    }
    
    @Test
    public void updateTest(){            
        doNothing().when(administratorDao).update(any(Administrator.class));        
        administrator.setFirstName("Ujo");
        administratorService.update(administrator);
        verify(administratorDao).update(administrator);
    }
    
    @Test
    public void deleteTest(){
        doNothing().when(administratorDao).delete(any(Administrator.class));        
        administratorService.delete(administrator);
        verify(administratorDao).delete(administrator);        
    }
    
    @Test
    public void getByIdTest(){
        Long id = administrator.getId();
        when(administratorDao.getById(any(Long.class))).thenReturn(administrator);        
        administratorService.getById(id);
        verify(administratorDao).getById(id);
    }
    
    @Test
    public void getAllTest(){     
        when(administratorDao.getAll()).thenReturn(administratorList);
        List<Administrator> administratorsDto = administratorService.getAll();
        int originalSize = administratorsDto.size();
        administratorList.add(administrator2);   
        int newSize = administratorsDto.size();    
        assertEquals(originalSize, newSize - 1);
        verify(administratorDao).getAll();
    }

    @Test
    public void listTripsWithoutExcursionsTest() {
        Assert.assertEquals(tripService.getAll().size(), 1);
        Assert.assertEquals(administratorService.getTripsWithoutExcursions().size(), 0);
        tripService.create(tripExcursionless);         
        Assert.assertEquals(tripService.getAll().size(), 2);
        Assert.assertEquals(administratorService.getTripsWithoutExcursions().size(), 1);
    }

    @Test
    public void listTripsUnavailble() {
        Assert.assertEquals(tripService.getAll().size(), 1);
        Assert.assertEquals(administratorService.getTripsUnavailble().size(), 0);
        tripService.create(tripUnavailable);  
        Assert.assertEquals(tripService.getAll().size(), 2);
        Assert.assertEquals(administratorService.getTripsUnavailble().size(), 1);
    }
}
