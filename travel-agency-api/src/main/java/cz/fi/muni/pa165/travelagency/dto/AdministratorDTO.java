package cz.fi.muni.pa165.travelagency.dto;

/**
 *
 * @author Michal Holic
 */
public class AdministratorDTO extends UserDTO {
    
    @Override
    public String toString() {
        return "AdministratorDTO{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + '}';
    }
}
