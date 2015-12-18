package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Michal Holic
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(getById(customer.getId()));
    }

    @Override
    public Customer getById(Long id) {
        return em.find(Customer.class, id);
    }        
    
    
    @Override
    public Customer getByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Cannot search Customer for a null e-mail");
        }

        try {
            return em.createQuery("SELECT c FROM Customer c where email=:email",
                    Customer.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getByUsername(String username){
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Cannot search Customer for a null username");
        }

        try {
            return em.createQuery("SELECT c FROM Customer c where username=:username",
                    Customer.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
