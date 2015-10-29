package cz.fi.muni.pa165.travelagency.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This class represents a trip which customer can reserve.
 * 
 * @author Michal Holic
 */
@Entity
public class Trip implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private Date dateFrom;
    
    @NotNull
    private Date dateTo;
    
    @NotNull
    @Column(nullable = false)
    private String destination;
    
    @NotNull
    @Column(nullable = false)
    private Integer numberOfAvailable;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
    private Set<Excursion> excursions = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "trips")
    private Set<Reservation> reservations = new HashSet<>();

    public Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }
    
    public void addExcursion(Excursion excursion) {
        this.excursions.add(excursion);
    }

    public Set<Excursion> getExcursions() {
        return Collections.unmodifiableSet(this.excursions);
    }
    
    public void removeExcursion(Excursion excursion) {
        this.excursions.remove(excursion);
    }
    
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(this.reservations);
    }
    
    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNumberOfAvailable() {
        return numberOfAvailable;
    }

    public void setNumberOfAvailable(Integer numberOfAvailable) {
        this.numberOfAvailable = numberOfAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trip{" + "dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", destination=" + destination + ", numberOfAvailable=" + numberOfAvailable + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.dateFrom);
        hash = 37 * hash + Objects.hashCode(this.dateTo);
        hash = 37 * hash + Objects.hashCode(this.destination);
        hash = 37 * hash + Objects.hashCode(this.numberOfAvailable);
        hash = 37 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Trip)) return false;
        final Trip other = (Trip) obj;
        if (!Objects.equals(this.dateFrom, other.getDateFrom())) return false;
        if (!Objects.equals(this.dateTo, other.getDateTo())) return false;
        if (!Objects.equals(this.destination, other.getDestination())) return false;
        if (!Objects.equals(this.numberOfAvailable, other.getNumberOfAvailable())) return false;
        if (!Objects.equals(this.price, other.getPrice())) return false;
        return true;
    }
}
