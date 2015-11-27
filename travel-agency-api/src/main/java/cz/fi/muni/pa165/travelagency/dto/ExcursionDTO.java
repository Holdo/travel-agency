package cz.fi.muni.pa165.travelagency.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.Objects;

/**
 *
 * @author Julius Stassik
 */
public class ExcursionDTO {
    
    private Long id;
    private Date date;
    private Duration duration;
    private String destination;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
