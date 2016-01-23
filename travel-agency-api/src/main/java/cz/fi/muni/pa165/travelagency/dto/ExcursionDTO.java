package cz.fi.muni.pa165.travelagency.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;

/**
 *
 * @author Julius Stassik
 */
public class ExcursionDTO {
    
    private Long id;

    private TripDTO trip;

    @NotNull
    private Date date;

    @NotNull
    private Duration duration;

    @NotNull
    private String destination;

    @NotNull
    @Min(0)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
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
        result = prime * result + ((trip == null) ? 0 : this.getTrip().hashCode());
        result = prime * result + ((date == null) ? 0 : this.getDate().hashCode());
        result = prime * result + ((destination == null) ? 0 : this.getDestination().hashCode());
        result = prime * result + ((duration == null) ? 0 : this.getDuration().hashCode());
        result = prime * result + ((price == null) ? 0 : this.getPrice().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ExcursionDTO)) {
            return false;
        }
        final ExcursionDTO other = (ExcursionDTO) obj;
        if (trip == null) {
            if (other.getTrip() != null) {
                return false;
            }
        } else if (!trip.equals(other.getTrip())) {
            return false;
        }
        if (date == null) {
            if (other.getDate() != null) {
                return false;
            }
        } else if (!date.equals(other.getDate())) {
            return false;
        }
        if (destination == null) {
            if (other.getDestination() != null) {
                return false;
            }
        } else if (!destination.equals(other.getDestination())) {
            return false;
        }
        if (duration == null) {
            if (other.getDuration() != null) {
                return false;
            }
        } else if (!duration.equals(other.getDuration())) {
            return false;
        }
        if (price == null) {
            if (other.getPrice() != null) {
                return false;
            }
        } else if (!price.equals(other.getPrice())) {
            return false;
        }
        return true;
    }
}
