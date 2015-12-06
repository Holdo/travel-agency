package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;

import java.util.List;

/**
 *
 * @author Diana Vilkolakova
 */
public interface CustomerService {
	void create(Customer customer);
	void update(Customer customer);
	void delete(Customer customer);
	List<Customer> getAll();
	Customer getById(Long id);
        Customer getByEmail(String email);
	long makeReservation(Customer customer, Trip trip);
}