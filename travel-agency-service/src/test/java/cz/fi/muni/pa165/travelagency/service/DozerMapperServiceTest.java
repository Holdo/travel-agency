package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cz.fi.muni.pa165.travelagency.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Holic
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class DozerMapperServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    DozerMapperService dms;
    
    private Customer customer;
    private Collection<Reservation> reservations;
    
    @BeforeMethod
    public void createCustomerWithReservation(){
        customer = new Customer();
        customer.setEmail("customer@test.com");
        customer.setFirstName("David");
        customer.setLastName("Hasselhoff");
        customer.setId(Long.decode("1"));
        customer.setPassword("password");
        customer.setUsername("Hoff");
        
        Reservation reservation = new Reservation();
        reservation.setId(Long.decode("1"));
        reservation.setCustomer(customer);
        reservation.setPrice(BigDecimal.valueOf(18500));
        reservation.setTrip(new Trip());
        
        customer.addReservation(reservation);

        reservations = new HashSet<>();
        reservations = customer.getReservations();
    }
    
    @Test
    public void collectionMappingTest(){
    	List<ReservationDTO> listOfDTOS = dms.mapTo(reservations, ReservationDTO.class);
    	Assert.assertEquals(listOfDTOS.get(0).getCustomer().getLastName(), "Hasselhoff");
    }
    
}