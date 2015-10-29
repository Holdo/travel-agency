package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.EmbeddedDatabaseSpringConfig;
import cz.fi.muni.pa165.travelagency.entity.Administrator;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Spring tests for UserDao
 * 
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private UserDao userDao;
    
    private User user1;
    private User user2;
    
    @BeforeMethod
    public void createUsers() {
        user1 = new Administrator();
        user2 = new Customer();
        
        user1.setEmail("user1@email.com");
        user1.setFirstName("Michal");
        user1.setLastName("Holic");
        user1.setPassword("password");
        user1.setUsername("holdo");
        
        user2.setEmail("user2@email.com");
        user2.setFirstName("Robert");
        user2.setLastName("Fico");
        user2.setPassword("password");
        user2.setUsername("hranol");
        
        userDao.createUser(user1);
        userDao.createUser(user2);
    }
    
    @Test
    public void usersCreation() {
        Administrator administrator = em.find(Administrator.class, user1.getId());
        Customer customer = em.find(Customer.class, user2.getId());
        Assert.assertTrue(administrator != null, "Administrator was not found in the database.");
        Assert.assertTrue(customer != null, "Customer was not found in the database.");
    }
}
