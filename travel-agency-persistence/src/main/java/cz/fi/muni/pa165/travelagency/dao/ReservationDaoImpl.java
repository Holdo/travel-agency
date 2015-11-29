package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Michal Holic
 */
@Repository
public class ReservationDaoImpl implements ReservationDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(Reservation reservation) {
        em.persist(reservation);
        return reservation.getId();
    }
    
    @Override
    public void update(Reservation reservation) {
        em.merge(reservation);
    }
    
    @Override
    public void delete(Reservation reservation) {
        em.remove(getById(reservation.getId()));
    }
    
    @Override
    public Reservation getById(Long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> getAll() {
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }
}
