package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;
import org.dozer.inject.Inject;
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
    private ReservationDao reservationDao;
    
    List<Reservation> reservations;
    
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
    public void makeReservation(Customer customer, Reservation reservation) {
        if (reservation.getTrip().getNumberOfAvailable() == 0) {
            //throw vynimku 
        }
        
        reservation.setCustomer(customer);
        reservation.getTrip().addReservation(reservation);
        reservation.getTrip().setNumberOfAvailable(reservation.getTrip().getNumberOfAvailable()-1);
        reservations.add(reservation);
        reservationDao.update(reservation);
    }
    
    
}
