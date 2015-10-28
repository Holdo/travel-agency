package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Implements UserDao.
 * 
 * @author Michal Holic
 */
@Repository //Not required for XML configuration, but tests use Annotations
public class UserDaoImpl implements UserDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public <T extends User> void createUser(T user) {
        em.persist(user);
    }
    
}
