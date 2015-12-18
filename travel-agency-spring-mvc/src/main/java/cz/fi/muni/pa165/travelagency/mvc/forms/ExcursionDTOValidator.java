package cz.fi.muni.pa165.travelagency.mvc.forms;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Michal Holic
 */
public class ExcursionDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExcursionDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ExcursionDTO excursionDTO = (ExcursionDTO) target;
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
		if (excursionDTO.getDate() == null) return;
		if (excursionDTO.getDuration() == null) return;
		if (excursionDTO.getDuration().toHours() <= 0) {
			errors.rejectValue("duration", null, "Duration must be at least 1 hour.");
		}
		if (excursionDTO.getDestination().equals("")) {
			errors.rejectValue("destination", null, "Destination can not be empty.");
		}
		if (excursionDTO.getDate().compareTo(currentDate) < 0) {
			errors.rejectValue("dateFrom", null, "It is not possible to add excursion with a past date.");
		}
	}

}
