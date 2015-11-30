package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.List;

import cz.fi.muni.pa165.travelagency.facade.ExcursionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class ExcursionFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	ExcursionFacade excursionFacade;

	private ExcursionDTO excursion;
	private TripDTO trip;

	@BeforeMethod
	public void prepareTestEntities() {
		trip = new TripDTO();
		trip.setDateFrom(Date.valueOf("2015-01-02"));
		trip.setDateTo(Date.valueOf("2015-05-06"));
		trip.setDestination("Trip destination");
		trip.setNumberOfAvailable(3);
		trip.setPrice(new BigDecimal("12000.50"));

		excursion = new ExcursionDTO();
		excursion.setDate(Date.valueOf("2015-01-02"));
		excursion.setDestination("Excursion destination");
		excursion.setDuration(Duration.ofHours(6));
		excursion.setPrice(new BigDecimal("1400.50"));
	}

	@Test
	public void crudFacadeExcursionTest() {
		excursionFacade.create(excursion);
		List<ExcursionDTO> excursionDTOs = excursionFacade.getAll();
		Assert.assertEquals(excursionDTOs.size(), 1);
		Long excursionId = excursionDTOs.get(0).getId();
		excursion.setId(excursionId);
		excursion.setDestination("New destination");
		excursionFacade.update(excursion);
		excursionDTOs = excursionFacade.getAll();
		Assert.assertEquals(excursionDTOs.size(), 1);
		Assert.assertEquals(excursionDTOs.get(0).getDestination(), excursion.getDestination());
		excursionFacade.delete(excursionId);
		boolean deleted = false;
		try {
			excursionFacade.getById(excursionId);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().equals("desired excursion does not exist"))
				deleted = true;
		}
		Assert.assertTrue(deleted);
	}
}