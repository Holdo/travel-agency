package cz.fi.muni.pa165.travelagency.ws;

import cz.fi.muni.pa165.travelagency.ws.generated.JavaSqlDateType;
import cz.fi.muni.pa165.travelagency.ws.generated.Trip;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Michal Holic
 */
@Component
public class TripRepository {

	private static final AtomicLong counter  = new AtomicLong(0);
	private static final List<Trip> trips = new ArrayList<>();

	@PostConstruct
	public void initData() {
		trips.add(createTrip(Date.valueOf("2016-02-01"), Date.valueOf("2016-03-01"), "Florida, USA", 10, BigDecimal.valueOf(19520)));
		trips.add(createTrip(Date.valueOf("2016-03-04"), Date.valueOf("2016-04-04"), "Moscow, Russia", 8, BigDecimal.valueOf(7230.50)));
		trips.add(createTrip(Date.valueOf("2016-04-07"), Date.valueOf("2016-05-07"), "Hurghada, Egypt", 7, BigDecimal.valueOf(14340.50)));
		trips.add(createTrip(Date.valueOf("2016-05-10"), Date.valueOf("2016-06-10"), "Barcelona, Spain", 12, BigDecimal.valueOf(4500)));
		trips.add(createTrip(Date.valueOf("2016-06-13"), Date.valueOf("2016-07-13"), "Crete, Greece", 5, BigDecimal.valueOf(7900.90)));
		trips.add(createTrip(Date.valueOf("2016-07-16"), Date.valueOf("2016-08-16"), "Tokyo, Japan", 6, BigDecimal.valueOf(27980)));
		trips.add(createTrip(Date.valueOf("2016-08-19"), Date.valueOf("2016-09-19"), "Sydney, Australia", 10, BigDecimal.valueOf(29999.90)));
		trips.add(createTrip(Date.valueOf("2016-09-22"), Date.valueOf("2016-10-22"), "Malé, Maldives", 11, BigDecimal.valueOf(48500)));
		trips.add(createTrip(Date.valueOf("2016-10-25"), Date.valueOf("2016-11-26"), "George Town, Cayman Islands", 25, BigDecimal.valueOf(42399.90)));
	}

	public Trip getTripByDestination(String destination) {
		Assert.notNull(destination);
		return trips.stream().filter(u -> u.getDestination().equalsIgnoreCase(destination)).findFirst().orElse(null);
	}

	public Trip getTripById(long id) {
		return trips.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
	}

	public List<Trip> getTrips() {
		return Collections.unmodifiableList(trips);
	}

	protected Trip createTrip(Date dateFrom, Date dateTo, String destination, int numberOfAvailable, BigDecimal price) {
		JavaSqlDateType javaSqlDateTypeFrom = new JavaSqlDateType();
		javaSqlDateTypeFrom.setDateString(dateFrom.toString());
		JavaSqlDateType javaSqlDateTypeTo = new JavaSqlDateType();
		javaSqlDateTypeTo.setDateString(dateTo.toString());

		Trip trip = new Trip();
		trip.setId(counter.incrementAndGet());
		trip.setDateFrom(javaSqlDateTypeFrom);
		trip.setDateTo(javaSqlDateTypeTo);
		trip.setDestination(destination);
		trip.setNumberOfAvailable(numberOfAvailable);
		trip.setPrice(price);
		return trip;
	}

}
