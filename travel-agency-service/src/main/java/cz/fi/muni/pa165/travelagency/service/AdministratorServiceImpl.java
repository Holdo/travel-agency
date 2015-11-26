package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.CustomerDao;
import cz.fi.muni.pa165.travelagency.dao.ExcursionDao;
import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;
import org.dozer.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Branislav Bohumel
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    
    @Inject
    private CustomerDao customerDao;
    @Inject
    private ReservationDao reservationDao;
    @Inject
    private TripDao tripDao;
    @Inject
    private ExcursionDao excursionDao;
    
    @Override
    public List<Customer> listCustomers() {
	return customerDao.findAll();        
    }

    @Override
    public List<Reservation> listReservations() {
	return reservationDao.findAll();        
    }

    @Override
    public void createTrip(Trip trip) {
        tripDao.create(trip);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripDao.update(trip);
    }

    @Override
    public void deleteTrip(Trip trip) {
        tripDao.delete(trip);
    }

    @Override
    public Trip findTripById(Long id) {
        return tripDao.findById(id);
    }

    @Override
    public List<Trip> listTrips() {
	return tripDao.findAll();        
    }

    @Override
    public void createExcursion(Excursion excursion) {
        excursionDao.create(excursion);
    }

    @Override
    public void updateExcursion(Excursion excursion) {
        excursionDao.update(excursion);
    }

    @Override
    public void deleteExcursion(Excursion excursion) {
        excursionDao.delete(excursion);
    }

    @Override
    public Excursion findExcursionById(Long id) {
        return excursionDao.findById(id);
    }

    @Override
    public List<Excursion> listExcursions() {
	return excursionDao.findAll();     
    }

}
