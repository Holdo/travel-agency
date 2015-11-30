package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.facade.ReservationFacade;
import cz.fi.muni.pa165.travelagency.service.CustomerService;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;
import cz.fi.muni.pa165.travelagency.service.ReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Julius Stassik
 */

@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private DozerMapperService dozerMapperService;

    @Override
    public void delete(Long id) {
        reservationService.delete(reservationService.getById(id));
    }

    @Override
    public void update(ReservationDTO reservationDTO) {
        Reservation reservation = dozerMapperService.mapTo(reservationDTO, Reservation.class);
        reservationService.update(reservation);
    }

    @Override
    public ReservationDTO getById(Long id) {
        return dozerMapperService.mapTo(reservationService.getById(id), ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getAll() {
        return dozerMapperService.mapTo(reservationService.getAll(), ReservationDTO.class);
    }
    
}
