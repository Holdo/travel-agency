package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.AdministratorDao;
import cz.fi.muni.pa165.travelagency.entity.Administrator;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.ArrayList;
import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Branislav Bohumel
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    
    @Autowired
    private AdministratorDao administratorDao;
    
    @Autowired
    private TripService tripService;
    
    @Override
    public void create(Administrator admin) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setRole(UserRole.ROLE_ADMIN);
        administratorDao.create(admin);
    }

    @Override
    public void update(Administrator admin) {
        if (!admin.getPassword().equals("") && admin.getPassword() != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        }
        administratorDao.update(admin);
    }

    @Override
    public void delete(Administrator admin) {
        administratorDao.delete(admin);
    }

    @Override
    public Administrator getById(Long id) {
        return administratorDao.getById(id);
    }

    @Override
    public List<Administrator> getAll() {
	return administratorDao.getAll();
    }

    @Override
    public List<Trip> getTripsWithoutExcursions() {
        List<Trip> tripListIn = tripService.getAll();
        List<Trip> tripListOut = new ArrayList<Trip>();
        for(Trip trip : tripListIn){
            if(trip.getExcursions().isEmpty()){
                tripListOut.add(trip);
            }
        }
        return tripListOut;
    }

    @Override
    public List<Trip> getTripsUnavailble() {
        List<Trip> tripListIn = tripService.getAll();
        List<Trip> tripListOut = new ArrayList<Trip>();
        for(Trip trip : tripListIn){
            if(trip.getNumberOfAvailable() == 0){
                tripListOut.add(trip);
            }
        }        
        return tripListOut;
    }
}