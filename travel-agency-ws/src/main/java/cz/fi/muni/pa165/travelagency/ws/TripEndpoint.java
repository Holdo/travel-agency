package cz.fi.muni.pa165.travelagency.ws;

import cz.fi.muni.pa165.travelagency.ws.exception.TripNotFoundException;
import cz.fi.muni.pa165.travelagency.ws.generated.GetTripRequestByDestination;
import cz.fi.muni.pa165.travelagency.ws.generated.GetTripResponse;
import cz.fi.muni.pa165.travelagency.ws.generated.GetTripsRequest;
import cz.fi.muni.pa165.travelagency.ws.generated.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 *
 * @author Michal Holic
 */
@Endpoint
public class TripEndpoint {

	private static final String NAMESPACE_URI = "http://muni.fi.cz/pa165/ws/entities/trips";

	@Autowired
	private TripRepository tripRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTripRequestByDestination")
	@ResponsePayload
	public GetTripResponse getTrip(@RequestPayload GetTripRequestByDestination request) {

		final GetTripResponse response = new GetTripResponse();
		final Trip trip = tripRepository.getTripByDestination(request.getDestination());

		if (trip == null) throw new TripNotFoundException(request.getDestination());

		response.getTrip().add(trip);
		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTripsRequest")
	@ResponsePayload
	public GetTripResponse getTrips(@RequestPayload GetTripsRequest request) {

		final GetTripResponse response = new GetTripResponse();
		final List<Trip> trips = tripRepository.getTrips();

		for (Trip trip : trips) {
			response.getTrip().add(trip);
		}

		return response;

	}
}
