package cz.fi.muni.pa165.travelagency.entity;

/**
 * ENUM defining user roles
 *
 * @author Michal Holic
 */
public enum UserRole {
	ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

	private final String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}
}
