package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
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
public class CustomerDaoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private CustomerDao customerDao;
    
    private Customer customer;
    
    @PersistenceContext
    private EntityManager em;
    
    @BeforeMethod
    public void createCustomer() {
        customer.setPassword("0000");
        customer.setUsername("HappyCustomer");
        customer.setEmail("happycustomer@gmail.com");
        customer.setFirstName("Happy");
        customer.setLastName("Customer");
        
        customerDao.create(customer);
    }
    
    
    @Test
    public void createTest() {
        Assert.assertNotNull(customerDao.findById(customer.getId()), "Customer doesn't exist!");
    }
    
    @Test
    public void findByIdTest() {
	Assert.assertNotNull(customerDao.findById(customer.getId()), "Customer wasn't found!");
    }
    
    @Test
    public void updateTest() {
        customer.setFirstName("Angry");
        customerDao.update(customer);
        Customer alteredCustomer = em.find(Customer.class, customer.getId());
        Assert.assertEquals(alteredCustomer.getFirstName(), "Angry", "Customer wasn't updated");
    }
    
    @Test
    public void deleteTest() {
        customerDao.delete(customer);
        Customer alteredCustomer = em.find(Customer.class, customer.getId());
        Assert.assertNull(alteredCustomer, "Customer wasn't deleted!");
    }
    
    @Test
    public void findAll() {                
        List<Customer> customers = customerDao.findAll();
	Assert.assertEquals(customers.size(), 1, "There's an incorrect amount of customers in the database!");
        Assert.assertTrue(customers.contains(customerDao.findById(customer.getId())), "Customer isn't present in the database!");
        
    }
    
}