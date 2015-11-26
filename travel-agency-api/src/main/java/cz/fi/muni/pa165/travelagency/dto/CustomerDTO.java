package cz.fi.muni.pa165.travelagency.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Michal Holic
 */
public class CustomerDTO extends UserDTO {
    private Set<ReservationDTO> reservations = new HashSet<>();

    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
    
    @Override
    public String toString() {
        return "CustomerDTO{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", reservations='" + reservations + '\''
                + '}';
    }
}
