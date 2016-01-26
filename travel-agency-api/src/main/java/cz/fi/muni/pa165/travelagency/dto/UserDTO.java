package cz.fi.muni.pa165.travelagency.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Michal Holic
 */
public abstract class UserDTO {

    protected Long id;
    
    @NotNull
    @Size(min=3, max=240,  message = "User name has incorrect length")
    protected String username;
    
    @Size(min=5, max=240,  message = "Password has incorrect length")
    protected String password;
    
    @NotNull
    @Pattern(regexp="^[a-z0-9A-Z0._%+-]+@[a-z0-9A-Z0.-]+\\.[a-zA-Z]{2,6}$",  message = "Incorrect email format")
    protected String email;
    
    @NotNull
    @Size(min=3, max=240,  message = "Last name has incorrect length")
    protected String lastName;
    
    @NotNull
    @Size(min=3, max=240,  message = "First name has incorrect length")
    protected String firstName;

    protected String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof UserDTO)) return false;
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.username, other.getUsername())) return false;
        if (!Objects.equals(this.email, other.getEmail())) return false;
        return true;
    }
}
