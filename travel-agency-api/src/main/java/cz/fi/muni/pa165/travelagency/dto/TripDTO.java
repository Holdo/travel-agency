package cz.fi.muni.pa165.travelagency.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Diana Vilkolakova
 */
public class TripDTO {

	private Long id;

	@NotNull
	private Date dateFrom;

	@NotNull
	private Date dateTo;

	@NotNull
	private String destination;

	@NotNull
	@Min(0)
	private Integer numberOfAvailable;

	@NotNull
	@Min(0)
	private BigDecimal price;

	private Set<ExcursionDTO> excursions = new HashSet<>();

	private Set<ReservationDTO> reservations = new HashSet<>();

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

	public Set<ExcursionDTO> getExcursions() {
		return excursions;
	}

	public void setExcursions(Set<ExcursionDTO> excursions) {
		this.excursions = excursions;
	}

	public Set<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationDTO> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "TripDTO{" +
				"id=" + id +
				", dateFrom=" + dateFrom +
				", dateTo=" + dateTo +
				", destination='" + destination + '\'' +
				", numberOfAvailable=" + numberOfAvailable +
				", price=" + price +
				", excursions=" + excursions +
				", reservations=" + reservations +
				'}';
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
		if (!(obj instanceof TripDTO)) return false;
		final TripDTO other = (TripDTO) obj;
		if (!Objects.equals(this.dateFrom, other.getDateFrom())) return false;
		if (!Objects.equals(this.dateTo, other.getDateTo())) return false;
		if (!Objects.equals(this.destination, other.getDestination())) return false;
		if (!Objects.equals(this.numberOfAvailable, other.getNumberOfAvailable())) return false;
		if (!Objects.equals(this.price, other.getPrice())) return false;
		return true;
	}
}