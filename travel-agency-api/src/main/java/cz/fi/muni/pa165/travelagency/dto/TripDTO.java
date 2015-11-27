package cz.fi.muni.pa165.travelagency.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Diana Vilkolakova
 */
public class TripDTO {

	private Long id;

	private Date dateFrom;

	private Date dateTo;

	private String destination;

	private Integer numberOfAvailable;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TripDTO)) return false;

		TripDTO tripDTO = (TripDTO) o;

		if (!getDateFrom().equals(tripDTO.getDateFrom())) return false;
		if (!getDateTo().equals(tripDTO.getDateTo())) return false;
		if (!getDestination().equals(tripDTO.getDestination())) return false;
		if (!getNumberOfAvailable().equals(tripDTO.getNumberOfAvailable())) return false;
		return getPrice().equals(tripDTO.getPrice());
	}

	@Override
	public int hashCode() {
		int result = getDateFrom().hashCode();
		result = 31 * result + getDateTo().hashCode();
		result = 31 * result + getDestination().hashCode();
		result = 31 * result + getNumberOfAvailable().hashCode();
		result = 31 * result + getPrice().hashCode();
		return result;
	}
}