package cz.fi.muni.pa165.travelagency.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.fi.muni.pa165.travelagency.entity.Excursion;

/**
 * dao implementation for Excursion entity
 * 
 * @author Julius Stassik
 */
@Repository
public class ExcursionDaoImpl implements ExcursionDao {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public void create(Excursion excursion) {
		em.persist(excursion);		
		
	}

	@Override
	public void delete(Excursion excursion) {
		em.remove(excursion);
		
	}

	@Override
	public void update(Excursion excursion) {
		em.merge(excursion);
		
	}

	@Override
	public Excursion findById(Long id) {
		return em.find(Excursion.class, id);
	}

	@Override
	public List<Excursion> findAll() {
		return em.createQuery("SELECT e FROM Excursion e", Excursion.class).getResultList();
	}
    
}
