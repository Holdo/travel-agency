package cz.fi.muni.pa165.travelagency.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * 
 * @author Julius Stassik
 */
@Entity
public class Excursion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Trip trip;

    @NotNull
    @Column(nullable = false)
    private Date date;

    @NotNull
    @Column(nullable = false)
    private Duration duration;

    @NotNull
    @Column(nullable = false)
    private String description;
    
    @NotNull
    @Column(nullable = false)
    private String destination;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    public Excursion () {
    }
    
    public Excursion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
                return trip;
    }

    public void setTrip(Trip trip) {
                this.trip = trip;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
    public String getDescription() {
        return description;
}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }	

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((date == null) ? 0 : this.getDate().hashCode());
            result = prime * result + ((description == null) ? 0 : this.getDescription().hashCode());
            result = prime * result + ((destination == null) ? 0 : this.getDestination().hashCode());
            result = prime * result + ((duration == null) ? 0 : this.getDuration().hashCode());
            result = prime * result + ((price == null) ? 0 : this.getPrice().hashCode());
            result = prime * result + ((trip == null) ? 0 : this.getTrip().hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (!(obj instanceof Excursion))
                    return false;
            final Excursion other = (Excursion) obj;
            if (date == null) {
                    if (other.getDate() != null)
                            return false;
            } else if (!date.equals(other.getDate()))
                    return false;
            if (description == null) {
                    if (other.getDescription() != null)
                            return false;
            } else if (!description.equals(other.getDescription()))
                    return false;
            if (destination == null) {
                if (other.getDestination() != null)
                        return false;
            } else if (!destination.equals(other.getDestination()))
                return false;
            if (duration == null) {
                if (other.getDuration() != null)
                        return false;
            } else if (!duration.equals(other.getDuration()))
                return false;
            if (price == null) {
                if (other.getPrice() != null)
                        return false;
            } else if (!price.equals(other.getPrice()))
                return false;
            if (trip == null) {
                if (other.getTrip() != null)
                        return false;
            } else if (!trip.equals(other.getTrip()))
                return false;
            return true;
    }
    
    @Override
    public String toString() {
            return "Excursion [id=" + id + ", trip=" + trip + ", date=" + date + ", duration=" + duration + ", description="
                            + description + ", destination=" + destination + ", price=" + price + "]";
    }

}