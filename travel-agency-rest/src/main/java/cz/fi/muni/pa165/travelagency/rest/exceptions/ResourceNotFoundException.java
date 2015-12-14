package cz.fi.muni.pa165.travelagency.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Michal Holic
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found.")
public class ResourceNotFoundException extends RuntimeException {

}
