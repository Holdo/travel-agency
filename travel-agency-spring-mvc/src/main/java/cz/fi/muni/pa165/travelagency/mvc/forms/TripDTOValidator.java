package cz.fi.muni.pa165.travelagency.mvc.forms;

import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Michal Holic
 */
public class TripDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TripDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TripDTO tripDTO = (TripDTO) target;
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
		if (tripDTO.getDateFrom() == null ||tripDTO.getDateTo() == null) return;
		if (tripDTO.getDestination().equals("")) {
			errors.rejectValue("destination", null, "Destination can not be empty.");
		}
		if (tripDTO.getDateFrom().compareTo(currentDate) <= 0) {
			errors.rejectValue("dateFrom", null, "It is not possible to add trip with a date from past.");
		}
		if (tripDTO.getDateTo().compareTo(currentDate) <= 0) {
			errors.rejectValue("dateTo", null, "It is not possible to add trip with a date from past.");
		}
		if (tripDTO.getDateFrom().compareTo(tripDTO.getDateTo()) >= 0) {
			errors.rejectValue("dateFrom", null, "Date from must be before date to.");
			errors.rejectValue("dateTo", null, "Date to must be after date from.");
		}
	}
}
