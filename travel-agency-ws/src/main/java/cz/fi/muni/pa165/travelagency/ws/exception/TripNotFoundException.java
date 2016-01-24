package cz.fi.muni.pa165.travelagency.ws.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 *
 * @author Michal Holic
 */
@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "Trip not found." )
public class TripNotFoundException extends RuntimeException {

	public TripNotFoundException(String tripDestination) {
		super("Could not find trip: " + tripDestination);
	}
}
