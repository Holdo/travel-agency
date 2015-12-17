package cz.fi.muni.pa165.travelagency.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.fi.muni.pa165.travelagency.SpringMvcConfigREST;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.rest.controllers.TripsController;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceNotFoundException;
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

import java.io.IOException;
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

	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(tripsController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void getTrip() throws Exception {
		TripDTO tripDTO = createTripDTO(1L, Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50));

		doReturn(tripDTO).when(tripFacade).getById(1L);

		mockMvc.perform(get("/trips/1")).andExpect(status().isOk())
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						jsonPath("$.[?(@.id==1)].destination").value("Hurghada, Egypt"));
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

	@Test
	public void createTrip() throws Exception {
		TripDTO tripDTO = new TripDTO();
		tripDTO.setDestination("Soul, South Korea,");
		String json = convertObjectToJson(tripDTO);
		mockMvc.perform(post("/trips/create").contentType(MediaType.APPLICATION_JSON)
						.content(json)).andExpect(status().isOk());
	}

	@Test
	public void updateTrip() throws Exception {
		TripDTO tripDTO = createTripDTO(1L, Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50));

		doReturn(tripDTO).when(tripFacade).getById(1L);
		doNothing().when(tripFacade).update(any(TripDTO.class));

		String json = convertObjectToJson(tripDTO);
		mockMvc.perform(put("/trips/1").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	@Test
	public void deleteTrip() throws Exception {
		mockMvc.perform(delete("/trips/2")).andExpect(status().isOk());
		doThrow(new ResourceNotFoundException()).when(tripFacade).delete(2L);
		mockMvc.perform(delete("/trips/2")).andExpect(status().isNotFound());
	}

	private List<TripDTO> createTrips() {
		return Arrays.asList(createTripDTO(1L, Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50)),
							 createTripDTO(2L, Date.valueOf("2016-07-16"), Date.valueOf("2016-08-16"), "Tokyo, Japan", 6, BigDecimal.valueOf(27980)));
	}

	private TripDTO createTripDTO(long id, Date dateFrom, Date dateTo, String destination, int numberOfAvailable, BigDecimal price) {
		TripDTO trip = new TripDTO();
		trip.setId(id);
		trip.setDateFrom(dateFrom);
		trip.setDateTo(dateTo);
		trip.setDestination(destination);
		trip.setNumberOfAvailable(numberOfAvailable);
		trip.setPrice(price);
		return trip;
	}

	private static String convertObjectToJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(object);
	}
}
