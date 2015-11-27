package cz.fi.muni.pa165.travelagency.exceptions;

/**
 * 
 * @author Michal Holic
 */
public class TravelAgencyServiceException extends RuntimeException {

    public TravelAgencyServiceException() {
        super();
    }

    public TravelAgencyServiceException(String message) {
        super(message);
    }

    public TravelAgencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyServiceException(Throwable cause) {
        super(cause);
    }

    public TravelAgencyServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
