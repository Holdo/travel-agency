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
    private Date excursionStartDay;
    private Duration duration;
    private String destination;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExcursionStartDay() {
        return excursionStartDay;
    }

    public void setExcursionStartDay(Date excursionStartDay) {
        this.excursionStartDay = excursionStartDay;
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
        int hash = 31;
        hash = 97 * hash + id.hashCode();
        hash = 97 * hash + Objects.hashCode(this.excursionStartDay);
        hash = 97 * hash + Objects.hashCode(this.duration);
        hash = 97 * hash + Objects.hashCode(this.destination);
        hash = 97 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExcursionDTO other = (ExcursionDTO) obj;
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.excursionStartDay, other.excursionStartDay)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    
    
    
}
