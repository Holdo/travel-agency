package cz.fi.muni.pa165.travelagency.rest;

import cz.fi.muni.pa165.travelagency.SpringMvcConfigREST;
import cz.fi.muni.pa165.travelagency.rest.controllers.MainController;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 *
 * @author Michal Holic
 */
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvcConfigREST.class})
public class MainControllerTest extends AbstractTestNGSpringContextTests {

	private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();

	@Test
	public void mainControllerTest() throws Exception {
		mockMvc.perform(get("/"))//.andDo(.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("trips_uri").value("/trips"));
	}
}
