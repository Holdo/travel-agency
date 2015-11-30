package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.dozer.MappingException;
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
	}

	@Test
	public void crudFacadeExcursionTest() {
		tripFacade.create(trip);
		List<TripDTO> tripDTOs = tripFacade.getAll();
		Assert.assertEquals(tripDTOs.size(), 1);
		Long tripId = tripDTOs.get(0).getId();
		trip.setId(tripId);
		trip.setDestination("New destination");
		tripFacade.update(trip);
		tripDTOs = tripFacade.getAll();
		Assert.assertEquals(tripDTOs.size(), 1);
		Assert.assertEquals(tripDTOs.get(0).getDestination(), trip.getDestination());
		tripFacade.delete(tripId);
		boolean deleted = false;
		try {
			tripFacade.getById(tripId);
		} catch (MappingException e) {
			//returned object does not exist so null is returned to dozer mapping
			deleted = true;
		}
		Assert.assertTrue(deleted);
	}
}