package cz.fi.muni.pa165.travelagency.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Reservation entity
 * 
 *
 * @author Julius Stassik
 */
@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional=false)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Trip> trips = new HashSet<>();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Trip> getTrips() {
        return Collections.unmodifiableSet(this.trips);
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.price);
        hash = 29 * hash + Objects.hashCode(this.customer);
        hash = 29 * hash + Objects.hashCode(this.trips);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Reservation)) return false;
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.price, other.getPrice())) return false;
        if (!Objects.equals(this.customer, other.getCustomer())) return false;
        if (!Objects.equals(this.trips, other.getTrips())) return false;
        return true;
    }
}