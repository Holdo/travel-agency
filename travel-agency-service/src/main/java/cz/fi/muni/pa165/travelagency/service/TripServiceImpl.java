package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.TripDao;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Julius Stassik
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripDao tripDao;
    
    @Override
    public Trip create(Trip trip){
        
        if(trip == null) throw new IllegalArgumentException("param trip is null");
        
        tripDao.create(trip);
        
        return trip;                        
    }
    
    @Override
    public void update(Trip trip){
        
        if(trip == null) throw new IllegalArgumentException("param trip is null");
        
        tripDao.update(trip);
        
    }
    
    @Override
    public void delete(Trip trip){
        
        if(trip == null) throw new IllegalArgumentException("param trip is null");
        
        tripDao.delete(trip);
        
    }
    
    @Override
    public Trip getById(Long id){
        
        if (id == null || id < 0) throw new IllegalArgumentException("param id is null or < 0");
        
        return tripDao.getById(id);
        
    }

    @Override
    public List<Trip> getAll(){
        
       return tripDao.getAll();
        
    }
}
