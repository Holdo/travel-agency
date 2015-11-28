package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Administrator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 * @author Branislav Bohumel
 */
@Repository
public class AdministratorDaoImpl  implements AdministratorDao {
    
    @PersistenceContext
    private EntityManager em;

    //CRUD and list functions for administrator
    
    @Override
    public void create(Administrator administrator){
        em.persist(administrator);
    }  
    
    @Override
    public Administrator getById(Long id){
        return em.find(Administrator.class, id);
    }  
    
    @Override
    public void update(Administrator administrator){
        em.merge(administrator);
    } 
    
    @Override
    public void delete(Administrator administrator){
        em.remove(getById(administrator.getId()));
    }
    
    @Override
    public List<Administrator> getAll(){
	return em.createQuery("SELECT a FROM Administrator a", Administrator.class).getResultList();
    }
    
}
