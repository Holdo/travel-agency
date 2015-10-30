package cz.fi.muni.pa165.travelagency.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import cz.fi.muni.pa165.travelagency.EmbeddedDatabaseSpringConfig;
import cz.fi.muni.pa165.travelagency.entity.Trip;

/**
 * Tests for TripDao
 * 
 * @author Julius Stassik
 */

@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class TripDaoTest {
	
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
		
		Date dateFrom1 = new Date(2015, 1, 2);
		Date dateFrom2 = new Date(2015, 3, 4);
		Date dateTo3 = new Date(2015, 5, 6);
		Date dateTo4 = new Date(2015, 7, 8);
		
		String destination1 = "Zemplinska Sirava";
		String destination2 = "Liptovska Mara";
		
		int numberOfAvailable1 = 3;
		int numberOfAvailable2 = 5;
		
		BigDecimal price1 = new BigDecimal("100");
		BigDecimal price2 = new BigDecimal("1000");
		
		trip1.setDateFrom(dateFrom1);
		trip1.setDateTo(dateTo3);
		trip1.setDestination(destination1);
		trip1.setNumberOfAvailable(numberOfAvailable1);
		trip1.setPrice(price1);
		
		trip2.setDateFrom(dateFrom2);
		trip2.setDateTo(dateTo4);
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
	
}
