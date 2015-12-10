package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.travelagency.entity.UserRole;
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
    
    @Autowired
    private ReservationDao reservationDao;
    
    private Customer customer;    
    private Reservation reservation;
    
    @PersistenceContext
    private EntityManager em;
    
    @BeforeMethod
    public void createCustomer() {
        
        customer = new Customer();
        
        customer.setPassword("0000");
        customer.setUsername("HappyCustomer");
        customer.setEmail("happycustomer@gmail.com");
        customer.setFirstName("Happy");
        customer.setLastName("Customer");
        customer.setRole(UserRole.ROLE_USER);
        
        customerDao.create(customer);
        
        reservation = new Reservation();        
        customer.addReservation(reservation);
        
    }
    
    
    @Test
    public void createTest() {
        Assert.assertNotNull(customerDao.getById(customer.getId()), "Customer doesn't exist!");
    }
    
    @Test
    public void findByIdTest() {
	    Assert.assertNotNull(customerDao.getById(customer.getId()), "Customer wasn't found!");
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
    public void findAllTest() {                
        List<Customer> customers = customerDao.getAll();
	    Assert.assertEquals(customers.size(), 1, "There's an incorrect amount of customers in the database!");
        Assert.assertTrue(customers.contains(customerDao.getById(customer.getId())), "Customer isn't present in the database!");
        
    }
    
    @Test
    public void reservationsTest() {                
        Set<Reservation> reservations = customer.getReservations();
	Assert.assertEquals(reservations.size(), 1, "There's an incorrect amount of reservations in the customer data!");      
    }
    
}