package cz.fi.muni.pa165.travelagency.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 * @author Michal Holic
 */
@RestController
@RequestMapping("/trips")
public class TripsController {

	@Autowired
	private TripFacade tripFacade;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final Collection<TripDTO> getTrips() throws JsonProcessingException {
		return tripFacade.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final TripDTO getTrip(@PathVariable("id") long id) throws Exception {
		TripDTO tripDTO = tripFacade.getById(id);
		if (tripDTO == null){
			throw new ResourceNotFoundException();
		}
		return tripDTO;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public final void createProduct(@RequestBody TripDTO trip) throws Exception {
		try {
			tripFacade.create(trip);
		} catch (Exception ex) {
			throw new ResourceAlreadyExistingException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void deleteProduct(@PathVariable("id") long id) throws Exception {
		try {
			tripFacade.delete(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}
}
