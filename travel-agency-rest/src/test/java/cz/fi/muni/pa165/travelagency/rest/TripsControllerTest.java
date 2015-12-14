package cz.fi.muni.pa165.travelagency.rest;

import cz.fi.muni.pa165.travelagency.SpringMvcConfigREST;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.rest.controllers.TripsController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Michal Holic
 */
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvcConfigREST.class})
public class TripsControllerTest extends AbstractTestNGSpringContextTests {

	@Mock
	private TripFacade tripFacade;

	@Autowired
	@InjectMocks
	private TripsController tripsController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(tripsController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void getAllTrips() throws Exception {

		doReturn(Collections.unmodifiableList(this.createTrips())).when(
				tripFacade).getAll();

		mockMvc.perform(get("/trips"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						jsonPath("$.[?(@.id==1)].destination").value("Hurghada, Egypt"))
				.andExpect(jsonPath("$.[?(@.id==2)].destination").value("Tokyo, Japan"));
	}

	private List<TripDTO> createTrips() {
		return Arrays.asList(createTrip(1L, Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50)),
							 createTrip(2L, Date.valueOf("2016-07-16"), Date.valueOf("2016-08-16"), "Tokyo, Japan", 6, BigDecimal.valueOf(27980)));
	}

	private TripDTO createTrip(long id, Date dateFrom, Date dateTo, String destination, int numberOfAvailable, BigDecimal price) {
		TripDTO trip = new TripDTO();
		trip.setId(id);
		trip.setDateFrom(dateFrom);
		trip.setDateTo(dateTo);
		trip.setDestination(destination);
		trip.setNumberOfAvailable(numberOfAvailable);
		trip.setPrice(price);
		return trip;
	}
}
