package cz.fi.muni.pa165.travelagency.service;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;

/**
 *
 * @author Diana Vilkolakova
 */
public interface CustomerService {
    public void create(Customer customer);
    public void update(Customer customer);
    public void delete(Customer customer);
    public List<Customer> findAll();
    public Customer findById(Long id);
    public void makeReservation(Customer customer, Reservation reservation);
}
