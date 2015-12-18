package cz.fi.muni.pa165.travelagency.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceHasConstraintsException;
import cz.fi.muni.pa165.travelagency.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
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
		if (tripDTO == null) {
			throw new ResourceNotFoundException();
		}
		return tripDTO;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public final void createTrip(@RequestBody TripDTO trip) throws Exception {
		try {
			tripFacade.create(trip);
		} catch (Exception ex) {
			throw new ResourceAlreadyExistingException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public final TripDTO updateTrip(@PathVariable("id") long id, @RequestBody TripDTO tripDTO) throws Exception {
		tripDTO.setId(id);
		try {
			tripFacade.update(tripDTO);
		} catch (JpaSystemException e) {
			throw new ResourceHasConstraintsException();
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
		return tripFacade.getById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void deleteTrip(@PathVariable("id") long id) throws Exception {
		try {
			tripFacade.delete(id);
		} catch (JpaSystemException e) {
			throw new ResourceHasConstraintsException();
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}
}
