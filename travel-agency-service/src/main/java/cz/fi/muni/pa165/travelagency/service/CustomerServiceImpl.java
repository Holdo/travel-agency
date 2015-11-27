package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;

import java.math.BigDecimal;
import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.exceptions.TravelAgencyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diana Vilkolakova
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private TripDao tripDao;

	@Autowired
	private ReservationDao reservationDao;

	@Override
	public void create(Customer customer) {
		customerDao.create(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer findById(Long id) {
		return customerDao.findById(id);
	}
        
        @Override
        public Customer findByEmail(String email) {
            return customerDao.findByEmail(email);
        }

	@Override
	public Reservation makeReservation(Customer customer, Trip trip) {
		if (trip.getNumberOfAvailable() == 0) {
			throw new TravelAgencyServiceException("Requested trip is no longer available.");
		}

		Reservation reservation = new Reservation();
		reservation.setCustomer(customer);
		reservation.setTrip(trip);

		BigDecimal price = trip.getPrice();
		for (Excursion excursion : trip.getExcursions()) {
			price = price.add(excursion.getPrice());
		}
		reservation.setPrice(price);

		reservationDao.create(reservation);

		customer.addReservation(reservation);
		update(customer);
		trip.addReservation(reservation);
		trip.setNumberOfAvailable(trip.getNumberOfAvailable()-1);
		tripDao.update(trip);

		return reservation;
	}
}