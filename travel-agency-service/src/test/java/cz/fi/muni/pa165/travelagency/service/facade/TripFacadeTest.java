package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/**
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class TripFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private TripFacade tripFacade;

	private TripDTO trip;
	private ExcursionDTO excursion;

	String date1 = "1952-01-02";
	String date2 = "2013-12-22";

	@BeforeMethod
	public void setup() {
		trip = new TripDTO();
		trip.setDateFrom(Date.valueOf(date1));
		trip.setDateTo(Date.valueOf(date2));
		trip.setDestination("Jaskyna");
		trip.setNumberOfAvailable(5);
		trip.setPrice(new BigDecimal("1050"));

		excursion = new ExcursionDTO();
		excursion.setDate(Date.valueOf("2015-01-02"));
		excursion.setDestination("Excursion destination");
		excursion.setDuration(Duration.ofHours(6));
		excursion.setPrice(new BigDecimal("1400.50"));

		Set<ExcursionDTO> excursions = new HashSet<>();
		excursions.add(excursion);

		trip.setExcursions(excursions);
	}

	@Test
	public void crudFacadeExcursionTest() {
		tripFacade.create(trip);
		List<TripDTO> tripDTOs = tripFacade.getAll();
		Assert.assertEquals(tripDTOs.get(0).getExcursions().size(), 0);
		Assert.assertEquals(tripDTOs.size(), 1);
		Long tripId = tripDTOs.get(0).getId();
		trip.setId(tripId);
		trip.setDestination("New destination");
		Assert.assertEquals(trip.getExcursions().size(), 1);
		tripFacade.update(trip);
		tripDTOs = tripFacade.getAll();
		TripDTO tripDTO = tripDTOs.get(0);
		Assert.assertEquals(tripDTOs.size(), 1);
		Assert.assertEquals(tripDTO.getExcursions().size(), 1);
		Assert.assertEquals(tripDTO.getDestination(), trip.getDestination());
		tripFacade.delete(tripId);
		Assert.assertNull(tripFacade.getById(tripId));
	}
}