package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.*;

import java.math.BigDecimal;
import java.util.List;

import cz.fi.muni.pa165.travelagency.exceptions.TravelAgencyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		customer.setRole(UserRole.ROLE_USER.toString());
		customerDao.create(customer);
	}

	@Override
	public void update(Customer customer) {
		if (!customer.getPassword().equals("") && customer.getPassword() != null) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		}
		customerDao.update(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public List<Customer> getAll() {
		return customerDao.getAll();
	}

	@Override
	public Customer getById(Long id) {
		return customerDao.getById(id);
	}
        
	@Override
	public Customer getByEmail(String email) {
            return customerDao.getByEmail(email);
        }
        
        @Override
	public Customer getByUsername(String username) {
            return customerDao.getByUsername(username);
        }

	@Override
	public long makeReservation(Customer customer, Trip trip) {
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

		long createdReservationId = reservationDao.create(reservation);

		customer.addReservation(reservation);
		customerDao.update(customer);
		trip.addReservation(reservation);
		trip.setNumberOfAvailable(trip.getNumberOfAvailable()-1);
		tripDao.update(trip);

		return createdReservationId;
	}
}