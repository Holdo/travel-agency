package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;

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
    private CustomerFacade customerFacade;

    @Autowired
    private TripFacade tripFacade;
    
    private CustomerDTO customerDto;
    private CustomerDTO customerDto2;
    private TripDTO tripDTO;

    String date1 = "1952-01-02";  
    String date2 = "2013-12-22";    
    
    @BeforeMethod
    public void setup(){
        customerDto = new CustomerDTO();
        customerDto.setFirstName("Jozef");
        customerDto.setLastName("Mak");
        customerDto.setUsername("Milion");
        customerDto.setPassword("Traktor");
        customerDto.setEmail("Milion@Clovek.sk");

        customerDto2 = new CustomerDTO();
        customerDto2.setFirstName("Niekto");
        customerDto2.setLastName("Dalsi");
        customerDto2.setUsername("dvesto");
        customerDto2.setPassword("Rafauek");
        customerDto2.setEmail("Krecik@Beran.sk");

        tripDTO = new TripDTO();
        tripDTO.setDateFrom(Date.valueOf(date1));
        tripDTO.setDateTo(Date.valueOf(date2));
        tripDTO.setDestination("Papuca");
        tripDTO.setNumberOfAvailable(8);
        tripDTO.setPrice(new BigDecimal("8"));
    }
    
    @Test
    public void findCustomerByEmailTest(){
        customerFacade.create(customerDto);
        assertEquals(customerFacade.findCustomerByEmail("vsetkoLenNieSkutocnyEmail"), null);
        assertEquals(customerFacade.findCustomerByEmail("Milion@Clovek.sk").getEmail(), customerDto.getEmail());
    }
    
    @Test
    public void findCustomerByIdTest(){
        customerFacade.create(customerDto);
        customerDto.setId(customerFacade.findCustomerByEmail(customerDto.getEmail()).getId());
        assertEquals(customerFacade.findCustomerById(customerDto.getId()).getId(), customerDto.getId());
    }
    
    @Test
    public void getAllCustomersTest(){
        customerFacade.create(customerDto);
        assertEquals(customerFacade.getAllCustomers().size(), 1);
        customerFacade.create(customerDto2);
        assertEquals(customerFacade.getAllCustomers().size(), 2);
    }
    
    @Test
    public void makeReservationTest(){
        customerFacade.create(customerDto);
        tripFacade.create(tripDTO);
        customerDto.setId(customerFacade.findCustomerByEmail(customerDto.getEmail()).getId());
        tripDTO.setId(tripFacade.getAll().get(0).getId());
        Long id = customerFacade.makeReservation(customerDto, tripDTO);
        assertEquals(id, new Long(1));
    }
}