package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.fi.muni.pa165.travelagency.entity.Reservation;

/**
 * Dao implementation for reservation entity
 * 
 * @author Julius Stassik
 */
@Repository
public class ReservationDaoImpl implements ReservationDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void create(Reservation reservation) {
		em.persist(reservation);
		
	}

	@Override
	public void delete(Reservation reservation) {
		em.remove(findById(reservation.getId()));
		
	}

	@Override
	public void update(Reservation reservation) {
		em.merge(reservation);
		
	}

	@Override
	public Reservation findById(Long id) {
		return em.find(Reservation.class, id);
	}

	@Override
	public List<Reservation> listAll() {
		return em.createQuery("Select r FROM Reservation r", 
				Reservation.class).getResultList();
	}

	
    
}

