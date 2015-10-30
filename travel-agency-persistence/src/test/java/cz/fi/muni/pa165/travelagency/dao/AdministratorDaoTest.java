package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Administrator;
import java.util.List;
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
 *
 * @author Branislav Bohumel
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
@TestExecutionListeners(inheritListeners = false, listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class AdministratorDaoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private AdministratorDao administratorDao;
    
    private Administrator administrator;
    
    @PersistenceContext
    private EntityManager em;
    
    @BeforeMethod
    public void createAdministrator() {
        
        administrator = new Administrator();
        
        administrator.setPassword("qwert");
        administrator.setUsername("Administrator");
        administrator.setEmail("administrator@email.com");
        administrator.setFirstName("Admin");
        administrator.setLastName("the Cruel");
        
        administratorDao.create(administrator);
    }
    
    
    @Test
    public void createTest() {
        Assert.assertNotNull(administratorDao.findById(administrator.getId()), "Administrator doesn't exist!");
    }
    
    @Test
    public void findByIdTest() {
	Assert.assertNotNull(administratorDao.findById(administrator.getId()), "Administrator wasn't found!");
    }
    
    @Test
    public void updateTest() {
        administrator.setLastName("the Vindictive");
        administratorDao.update(administrator);
        Administrator alteredAdministrator = em.find(Administrator.class, administrator.getId());
        Assert.assertEquals(alteredAdministrator.getLastName(), "the Vindictive", "Administrator wasn't updated");
    }
    
    @Test
    public void deleteTest() {
        administratorDao.delete(administrator);
        Administrator alteredAdministrator = em.find(Administrator.class, administrator.getId());
        Assert.assertNull(alteredAdministrator, "Administrator wasn't deleted!");
    }
    
    @Test
    public void findAll() {                
        List<Administrator> administrators = administratorDao.findAll();
	Assert.assertEquals(administrators.size(), 1, "There's an incorrect amount of administrators in the database!");
        Assert.assertTrue(administrators.contains(administratorDao.findById(administrator.getId())), "Administrator isn't present in the database!");
        
    }
    
}