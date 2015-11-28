package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.List;
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
 * Tests for ExcursionDao
 * 
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ExcursionDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private ExcursionDao excursionDao;
    
    @Autowired
    private TripDao tripDao;
    
    private Excursion excursion;
    private Trip trip;
    private String dateOfExcursion = "2010-11-02"; //YYYY-MM-DD
    private String dateFrom = "2010-11-01"; //YYYY-MM-DD
    private String dateTo = "2010-11-07"; //YYYY-MM-DD
    
    @BeforeMethod
    public void createExcursions() {
        excursion = new Excursion();
        excursion.setDate(Date.valueOf(dateOfExcursion));
        excursion.setDestination("Some nice islands.");
        excursion.setDuration(Duration.ofHours(8));
        excursion.setPrice(BigDecimal.valueOf(1250.50));
        
        trip = new Trip();
        trip.setDateFrom(Date.valueOf(dateFrom));
        trip.setDateTo(Date.valueOf(dateTo));
        trip.setDestination("Carribean.");
        trip.setNumberOfAvailable(4);
        trip.setPrice(BigDecimal.valueOf(17450));
        tripDao.create(trip);
        
        excursion.setTrip(trip);
        
        excursionDao.create(excursion);
    }
    
    @Test
    public void databaseDoesNotContainExcursion() {
        Excursion excursionFromDB = excursionDao.getById(excursion.getId());
        Assert.assertNotNull(excursionFromDB, "Excursion was not saved in the DB.");
        Assert.assertEquals(excursionFromDB, excursion, "Excursion from DB does not match Excursion which was saved to DB.");
    }
    
    @Test
    public void excursionDoesNotContainTrip() {
        Excursion excursionFromDB = excursionDao.getById(excursion.getId());
        Assert.assertNotNull(excursionFromDB.getTrip(), "Trip is not in the Excursion.");
    }
    
    @Test
    public void updateExcursionTest() {
        excursion.setDestination("New destination");
        excursionDao.update(excursion);
        Excursion excursionFromDB = em.find(Excursion.class, excursion.getId());
        Assert.assertEquals(excursionFromDB.getDestination(), "New destination");
    }
    
    @Test
    public void findAllTest() {
        List<Excursion> listOfExcursionsFromDB = excursionDao.getAll();
        Assert.assertEquals(listOfExcursionsFromDB.size(), 1, "There's an incorrect amount of excursions in the database!");
        Assert.assertTrue(listOfExcursionsFromDB.contains(excursion), "Can't find list of excursions in the DB.");
    }
    
    @Test
    public void removeExcursionTest() {
        excursionDao.delete(excursion);
        Excursion excursionFromDB = em.find(Excursion.class, excursion.getId());
        Assert.assertNull(excursionFromDB);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void excursionPriceNull() {
        Excursion e = new Excursion();
        e.setPrice(null);
        e.setTrip(trip);
        e.setDate(Date.valueOf(dateOfExcursion));
        e.setDestination("Some nice islands");
        e.setDuration(Duration.ofHours(8));
        excursionDao.create(e);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void excursionDateNull() {
        Excursion e = new Excursion();
        e.setDate(null);
        e.setTrip(trip);
        e.setDestination("Some nice islands");
        e.setDuration(Duration.ofHours(8));
        e.setPrice(BigDecimal.valueOf(1250.50));
        excursionDao.create(e);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void excursionDurationNull() {
        Excursion e = new Excursion();
        e.setDuration(null);
        e.setTrip(trip);
        e.setDate(Date.valueOf(dateOfExcursion));
        e.setDestination("Some nice islands");
        e.setPrice(BigDecimal.valueOf(1250.50));
        excursionDao.create(e);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void excursionDestinationNull() {
        Excursion e = new Excursion();
        e.setDestination(null);
        e.setTrip(trip);
        e.setDate(Date.valueOf(dateOfExcursion));
        e.setDuration(Duration.ofHours(8));
        e.setPrice(BigDecimal.valueOf(1250.50));
        excursionDao.create(e);
    }
    
    @Test
    public void idsAreSame() {
        Excursion e1 = new Excursion();
        e1.setTrip(trip);
        e1.setDate(Date.valueOf(dateOfExcursion));
        e1.setDestination("Some nice islands");
        e1.setDuration(Duration.ofHours(8));
        e1.setPrice(BigDecimal.valueOf(1250.50));
        excursionDao.create(e1);
        
        Excursion e2 = new Excursion();
        e2.setTrip(trip);
        e2.setDate(Date.valueOf(dateOfExcursion));
        e2.setDestination("Some nice islands");
        e2.setDuration(Duration.ofHours(8));
        e2.setPrice(BigDecimal.valueOf(1250.50));
        excursionDao.create(e2);
        
        Assert.assertTrue(excursionDao.getById(e1.getId()).getId() != excursionDao.getById(e2.getId()).getId(),
                "Ids of two Excursion entities in DB are the same.");
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void excursionPriceNegative() {
        Excursion e = new Excursion();
        e.setPrice(BigDecimal.valueOf(-1.50));
        e.setTrip(trip);
        e.setDate(Date.valueOf(dateOfExcursion));
        e.setDestination("Some nice islands");
        e.setDuration(Duration.ofHours(8));
        excursionDao.create(e);
    }
}
