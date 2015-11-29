package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.facade.ReservationFacade;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author Julius Stassik
 */

@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class ReservationFacadeTest extends AbstractTransactionalTestNGSpringContextTests {       
    
    @Autowired
    private ReservationFacade reservationFacade;
    private ReservationDTO reservationDTO;
    
    private CustomerDTO customerDTO = new CustomerDTO();
    private TripDTO tripDTO = new TripDTO();
    
    
    @BeforeMethod
    public void setup(){
        customerDTO.setEmail("tu@tam.sk");
        customerDTO.setFirstName("Ignac");
        customerDTO.setLastName("Studeny");
        customerDTO.setPassword("123456");
        customerDTO.setUsername("ignacko");

        tripDTO.setDateFrom(Date.valueOf("2015-12-01"));
        tripDTO.setDateTo(Date.valueOf("2015-12-12"));
        tripDTO.setDestination("Afrika");
        tripDTO.setNumberOfAvailable(4);
        tripDTO.setPrice(new BigDecimal("1000"));
        
        reservationDTO = new ReservationDTO();
        reservationDTO.setCustomer(customerDTO);
        reservationDTO.setTrip(tripDTO);
        reservationDTO.setPrice(new BigDecimal("1100"));
        
    }
    
    
    @Test
    public void crudTest(){
        reservationFacade.create(reservationDTO);
        List<ReservationDTO> reservationsDTOs = reservationFacade.getAll();
        Assert.assertEquals(reservationsDTOs.size(), 1);
        Long reservationID = reservationsDTOs.get(0).getId();
        reservationDTO.setId(reservationID);
        reservationDTO.setPrice(new BigDecimal("500"));
        reservationFacade.update(reservationDTO);
        reservationsDTOs = reservationFacade.getAll();
        Assert.assertEquals(reservationsDTOs.size(), 1);
        Assert.assertEquals(reservationsDTOs.get(0).getPrice(), reservationDTO.getPrice());
        reservationFacade.delete(reservationID);
        boolean deleted = false;
        try{
            reservationFacade.getById(reservationID);
        } catch (MappingException e){
            deleted = true;
        }
        Assert.assertTrue(deleted);
    }
    
}
