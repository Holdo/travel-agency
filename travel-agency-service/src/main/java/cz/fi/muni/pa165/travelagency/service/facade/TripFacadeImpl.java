package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;
import cz.fi.muni.pa165.travelagency.service.TripService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Branislav Bohumel
 */
@Service
@Transactional
public class TripFacadeImpl implements TripFacade {
    
    @Autowired
    private TripService tripService;    
    @Autowired
    private DozerMapperService dozerMapperService;
    
    @Override
    public void create(TripDTO trip) {    
        tripService.create(dozerMapperService.mapTo(trip, Trip.class));
    }

    @Override
    public void delete(Long id) {
        tripService.delete(tripService.getById(id));
    }

    @Override
    public void update(TripDTO trip) {
        tripService.update(dozerMapperService.mapTo(trip, Trip.class));
    }

    @Override
    public TripDTO getById(Long id) {
        return dozerMapperService.mapTo(tripService.getById(id), TripDTO.class);
    }

    @Override
    public List<TripDTO> getAll() {
        return dozerMapperService.mapTo(tripService.getAll(), TripDTO.class);
    }
    
}