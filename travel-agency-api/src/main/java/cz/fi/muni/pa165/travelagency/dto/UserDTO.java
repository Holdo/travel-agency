package cz.fi.muni.pa165.travelagency.dto;

import java.util.Objects;

/**
 *
 * @author Michal Holic
 */
public abstract class UserDTO {

    protected Long id;
    protected String username;
    protected String password;
    protected String email;
    protected String lastName;
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
