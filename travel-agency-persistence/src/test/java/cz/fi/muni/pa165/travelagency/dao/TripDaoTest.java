package cz.fi.muni.pa165.travelagency.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Tests for TripDao
 * 
 * @author Julius Stassik
 */

@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class TripDaoTest extends AbstractTestNGSpringContextTests {
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	TripDao tripDao;
	
	Trip trip1;
	Trip trip2;	
	
	@BeforeMethod
	public void createTrips(){
		trip1 = new Trip();
		trip2 = new Trip();
		
		//YYYY-MM-DD
		String dateFrom1 = "2015-01-02";  
		String dateFrom2 = "2015-03-04"; 
		String dateTo3 = "2015-05-06";  
		String dateTo4 = "2015-07-08"; 
		
		
		String destination1 = "Zemplinska Sirava";
		String destination2 = "Liptovska Mara";
		
		int numberOfAvailable1 = 3;
		int numberOfAvailable2 = 5;
		
		BigDecimal price1 = new BigDecimal("100");
		BigDecimal price2 = new BigDecimal("1000");
		
		trip1.setDateFrom(Date.valueOf(dateFrom1));
		trip1.setDateTo(Date.valueOf(dateTo3));
		trip1.setDestination(destination1);
		trip1.setNumberOfAvailable(numberOfAvailable1);
		trip1.setPrice(price1);
		
		trip2.setDateFrom(Date.valueOf(dateFrom2));
		trip2.setDateTo(Date.valueOf(dateTo4));
		trip2.setDestination(destination2);
		trip2.setNumberOfAvailable(numberOfAvailable2);
		trip2.setPrice(price2);	
		
		tripDao.create(trip1);
		tripDao.create(trip2);
					
	}
	
	@Test
	public void find(){
		Trip foundTrip = tripDao.findById(trip1.getId());
		Assert.assertEquals(foundTrip, trip1);
		
	}
	
	@Test
	public void findAll(){
		List<Trip> trips = tripDao.findAll();
		Assert.assertEquals(trips.size(), 2);
	}
	
	@Test
	public void update(){
		trip1.setDestination("Somewhere");
		tripDao.update(trip1);
		Trip foundTrip = tripDao.findById(trip1.getId());
		Assert.assertEquals(foundTrip, trip1);
		
	}
	
	@Test
	public void remove(){
		tripDao.delete(trip1);
		Assert.assertNull(tripDao.findById(trip1.getId()));
		
	}
	
	@Test(expectedExceptions = ConstraintViolationException.class)	
	public void createTripWithNullField(){
		Trip trip3 = new Trip();
		trip3.setDateFrom(null);		
		tripDao.create(trip3);
				
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void removeNonExistEntity(){
		Trip trip4 = new Trip();
		
		String dateFrom5 = "2015-09-11"; 
		String dateTo6 = "2015-12-12";  
		
		trip4.setDateFrom(Date.valueOf(dateFrom5));
		trip4.setDateTo(Date.valueOf(dateTo6));		
		trip4.setDestination("Krivan");		
		trip4.setNumberOfAvailable(5);
		trip4.setPrice(new BigDecimal("500"));
		
		tripDao.delete(trip4);
		
	}
	
}