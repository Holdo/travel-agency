
package cz.fi.muni.pa165.travelagency.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Branislav Bohumel
 */
public class ReservationDTO {
    
    private Long id;
    private BigDecimal price;
    private UserDTO customer;
    private TripDTO trip;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.price);
        hash = 29 * hash + Objects.hashCode(this.customer);
        hash = 29 * hash + Objects.hashCode(this.trip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof ReservationDTO)) return false;
        final ReservationDTO other = (ReservationDTO) obj;
        if (!Objects.equals(this.price, other.getPrice())) return false;
        if (!Objects.equals(this.customer, other.getCustomer())) return false;
        if (!Objects.equals(this.trip, other.getTrip())) return false;
        return true;
    }

}