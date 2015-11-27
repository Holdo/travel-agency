package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.dto.UserDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Reservation;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.service.CustomerService;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 
 */
@Service
@Transactional
public class CustomerFacadeImpl extends UserFacadeImpl implements CustomerFacade {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private DozerMapperService dms;

    @Override
    public long makeReservation(CustomerDTO customerDTO, TripDTO tripDTO) {
        Reservation createdReservation = customerService.makeReservation(dms.mapTo(customerDTO, Customer.class), dms.mapTo(tripDTO, Trip.class));
        return createdReservation.getId();
    }

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer customer = customerService.findById(customerId);
        return (customer == null) ? null : dms.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer customer = customerService.findByEmail(email);
        return (customer == null) ? null : dms.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return dms.mapTo(customerService.findAll(), CustomerDTO.class);
    }
}