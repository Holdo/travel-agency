package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.ReservationDao;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Holic
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationDao reservationDao;
    
    @Override
    public void create(Reservation reservation) {
        reservationDao.create(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);
    }

    @Override
    public Reservation getById(Long id) {
        return reservationDao.getById(id);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationDao.getAll();
    }
}
