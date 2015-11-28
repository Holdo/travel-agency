package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.service.CustomerService;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Branislav Bohumel
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class CustomerFacadeTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    private DozerMapperService dms;
        
    @Autowired
    private CustomerFacade customerFacade;
    
    @Autowired
    private CustomerService customerService;
    
    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDto;
    private Trip trip;
    
    String date1 = "1952-01-02";  
    String date2 = "2013-12-22";    
    
    @BeforeMethod
    public void setup(){       
        customer = new Customer();
        customer.setFirstName("Jozef");
        customer.setLastName("Mak");
        customer.setUsername("Milion");
        customer.setPassword("Traktor");
        customer.setEmail("Milion@Clovek.sk");       
        
        customer2 = new Customer();
        customer2.setFirstName("Niekto");
        customer2.setLastName("Dalsi");
        customer2.setUsername("Dvesto");
        customer2.setPassword("Rafauek");
        customer2.setEmail("Krecik@Beran.sk");   
        
        customerService.create(customer);  
        
        customerDto = new CustomerDTO();
        customerDto.setFirstName("Jozef");
        customerDto.setLastName("Mak");
        customerDto.setUsername("Milion");
        customerDto.setPassword("Traktor");
        customerDto.setEmail("Milion@Clovek.sk");     
        
        trip = new Trip();
        trip.setDateFrom(Date.valueOf(date1));
        trip.setDateTo(Date.valueOf(date2));
        trip.setDestination("Papuca");
        trip.setNumberOfAvailable(8);
        trip.setPrice(new BigDecimal("8"));                  
    }
    
    @Test
    public void findCustomerByEmailTest(){     
        assertEquals(customerFacade.findCustomerByEmail("vsetkoLenNieSkutocnyEmail"), null);
        assertEquals(customerFacade.findCustomerByEmail("Milion@Clovek.sk").getEmail(), customer.getEmail());
    }
    
    @Test
    public void findCustomerByIdTest(){     
        assertEquals(customerFacade.findCustomerById(customer.getId()).getId(), customer.getId());
    }
    
    @Test
    public void getAllCustomersTest(){     
        assertEquals(customerFacade.getAllCustomers().size(), 1);
        customerService.create(customer2);  
        assertEquals(customerFacade.getAllCustomers().size(), 2);
    }
    
    @Test
    public void makeReservationTest(){          
        Long id = customerFacade.makeReservation(dms.mapTo(customer, CustomerDTO.class), dms.mapTo(trip, TripDTO.class));
        assertEquals(id, new Long(1));
    }
}