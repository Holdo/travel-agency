package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import org.hibernate.service.spi.ServiceException;
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
public class ExcursionFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    ExcursionFacade excursionFacade;
    
    private ExcursionDTO excursion = new ExcursionDTO();
    private TripDTO trip = new TripDTO();
    
    @BeforeMethod
    public void prepareTestEntities(){
        trip.setDateFrom(Date.valueOf("2015-01-02"));
        trip.setDateTo(Date.valueOf("2015-05-06"));
        trip.setDestination("Trip destination");
        trip.setNumberOfAvailable(3);
        trip.setPrice(new BigDecimal("12000.50"));
        
        excursion.setDate(Date.valueOf("2015-01-02"));
        excursion.setDestination("Excursion destination");
        excursion.setDuration(Duration.ofHours(6));
        excursion.setPrice(new BigDecimal("1400.50"));
    }
    
    /*@Test
    public void createExcursion(){
        excursionFacade.create(excursion);
    }*/
}
