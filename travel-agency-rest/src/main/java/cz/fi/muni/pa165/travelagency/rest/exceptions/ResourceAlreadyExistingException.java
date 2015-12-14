package cz.fi.muni.pa165.travelagency.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Michal Holic
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="The requested resource already exists.")
public class ResourceAlreadyExistingException extends RuntimeException {
}
