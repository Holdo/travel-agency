package cz.fi.muni.pa165.travelagency.mvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Michal Holic
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
