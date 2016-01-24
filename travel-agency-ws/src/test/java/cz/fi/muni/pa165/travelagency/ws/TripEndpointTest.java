package cz.fi.muni.pa165.travelagency.ws;

import cz.fi.muni.pa165.travelagency.ws.generated.JavaSqlDateType;
import cz.fi.muni.pa165.travelagency.ws.generated.Trip;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.transform.Source;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;
import static org.springframework.ws.test.server.ResponseMatchers.serverOrReceiverFault;

/**
 *
 * @author Michal Holic
 */
@WebAppConfiguration
@ContextConfiguration(classes = {WsConfig.class})
public class TripEndpointTest extends AbstractTestNGSpringContextTests {

	@Mock
	private TripRepository tripRepository;

	@Autowired
	@InjectMocks
	private TripEndpoint tripEndpoint;

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@BeforeClass
	public void createClient() {
		MockitoAnnotations.initMocks(this);
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void tripEndpointGetTripRequestNoTrip() throws Exception {

		doReturn(null).when(tripRepository).getTripByDestination("Not a valid destination");

		Source requestPayload = new StringSource(
				"<getTripRequestByDestination xmlns='http://muni.fi.cz/pa165/ws/entities/trips'>"
						+ "<destination>Not a valid destination</destination>"
						+ "</getTripRequestByDestination>");

		mockClient.sendRequest(withPayload(requestPayload)).andExpect(serverOrReceiverFault("Trip not found."));
	}

	@Test
	public void tripEndpointGetTripRequest() throws Exception {

		final List<Trip> trips = this.createTrips();
		doReturn(trips.get(0)).when(tripRepository).getTripByDestination("Florida, USA");

		Source requestPayload = new StringSource(
				"<getTripRequestByDestination xmlns='http://muni.fi.cz/pa165/ws/entities/trips'>"
						+ "<destination>Florida, USA</destination>"
						+ "</getTripRequestByDestination>");

		Source responsePayload = new StringSource(
				"<ns2:getTripResponse xmlns:ns2=\"http://muni.fi.cz/pa165/ws/entities/trips\">" +
						"<ns2:trip><ns2:id>1</ns2:id><ns2:dateFrom><dateString>2016-02-01</dateString></ns2:dateFrom><ns2:dateTo><dateString>2016-03-01</dateString></ns2:dateTo><ns2:destination>Florida, USA</ns2:destination><ns2:numberOfAvailable>10</ns2:numberOfAvailable><ns2:price>19520</ns2:price></ns2:trip>" +
						"</ns2:getTripResponse>");

		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
	}

	@Test
	public void tripEndpointGetAllPTripsRequest() throws Exception {

		doReturn(Collections.unmodifiableList(this.createTrips())).when(tripRepository).getTrips();

		Source requestPayload = new StringSource(
				"<getTripsRequest xmlns='http://muni.fi.cz/pa165/ws/entities/trips'>"
						+ "</getTripsRequest>");

		Source responsePayload = new StringSource(
				"<ns2:getTripResponse xmlns:ns2=\"http://muni.fi.cz/pa165/ws/entities/trips\">" +
						"<ns2:trip><ns2:id>1</ns2:id><ns2:dateFrom><dateString>2016-02-01</dateString></ns2:dateFrom><ns2:dateTo><dateString>2016-03-01</dateString></ns2:dateTo><ns2:destination>Florida, USA</ns2:destination><ns2:numberOfAvailable>10</ns2:numberOfAvailable><ns2:price>19520</ns2:price></ns2:trip>" +
						"<ns2:trip><ns2:id>2</ns2:id><ns2:dateFrom><dateString>2016-03-04</dateString></ns2:dateFrom><ns2:dateTo><dateString>2016-04-04</dateString></ns2:dateTo><ns2:destination>Moscow, Russia</ns2:destination><ns2:numberOfAvailable>8</ns2:numberOfAvailable><ns2:price>7230.5</ns2:price></ns2:trip>" +
						"<ns2:trip><ns2:id>3</ns2:id><ns2:dateFrom><dateString>2016-04-07</dateString></ns2:dateFrom><ns2:dateTo><dateString>2016-05-07</dateString></ns2:dateTo><ns2:destination>Hurghada, Egypt</ns2:destination><ns2:numberOfAvailable>7</ns2:numberOfAvailable><ns2:price>14340.5</ns2:price></ns2:trip>" +
						"</ns2:getTripResponse>");

		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
	}

	/**
	 * Helper method for mocking the trips
	 * @return list of trips
	 */
	private List<Trip> createTrips() {

		final List<Trip> trips = new ArrayList<>();

		trips.add(createTrip(1L, Date.valueOf("2016-02-01"), Date.valueOf("2016-03-01"), "Florida, USA", 10, BigDecimal.valueOf(19520)));
		trips.add(createTrip(2L, Date.valueOf("2016-03-04"), Date.valueOf("2016-04-04"), "Moscow, Russia", 8, BigDecimal.valueOf(7230.50)));
		trips.add(createTrip(3L, Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50)));

		return trips;
	}

	private Trip createTrip(long id, Date dateFrom, Date dateTo, String destination, int numberOfAvailable, BigDecimal price) {
		JavaSqlDateType javaSqlDateTypeFrom = new JavaSqlDateType();
		javaSqlDateTypeFrom.setDateString(dateFrom.toString());
		JavaSqlDateType javaSqlDateTypeTo = new JavaSqlDateType();
		javaSqlDateTypeTo.setDateString(dateTo.toString());

		final Trip trip = new Trip();
		trip.setId(id);
		trip.setDateFrom(javaSqlDateTypeFrom);
		trip.setDateTo(javaSqlDateTypeTo);
		trip.setDestination(destination);
		trip.setNumberOfAvailable(numberOfAvailable);
		trip.setPrice(price);
		return trip;
	}
}
