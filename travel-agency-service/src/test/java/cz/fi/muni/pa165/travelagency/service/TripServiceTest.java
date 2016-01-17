package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;

/**
 *
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class TripServiceTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private TripDao tripDao;

	private Excursion excursion;
	private Trip trip;

	@BeforeMethod
	public void prepareTestEntities(){
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
	public void create() {
		tripDao.create(trip);
		Trip tripFromDB = tripDao.getById(trip.getId());
		Assert.assertEquals(tripFromDB.getExcursions().size(), 1);
	}
}
