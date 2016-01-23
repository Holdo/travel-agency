package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
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
    public void create(TripDTO tripDTO) {
        tripService.create(dozerMapperService.mapTo(tripDTO, Trip.class));
    }

    @Override
    public void delete(Long id) {
        tripService.delete(tripService.getById(id));
    }

    @Override
    public void update(TripDTO tripDTO) {
        Trip trip = dozerMapperService.mapTo(tripDTO, Trip.class);
        if (tripDTO.getExcursions().size() != 0) {
            List<Excursion> excursions = dozerMapperService.mapTo(tripDTO.getExcursions(), Excursion.class);
            excursions.forEach(trip::addExcursion);
        }
        tripService.update(trip);
    }

    @Override
    public TripDTO getById(Long id) {
        Trip trip = tripService.getById(id);
        if (trip == null) return null;
        return dozerMapperService.mapTo(trip, TripDTO.class);
    }

    @Override
    public List<TripDTO> getAll() {
        return dozerMapperService.mapTo(tripService.getAll(), TripDTO.class);
    }
    
}