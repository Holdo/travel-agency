package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.dto.UserDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
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
    private DozerMapperService dozerMapperService;

    @Override
    public long makeReservation(CustomerDTO customerDTO, TripDTO tripDTO) {
        return customerService.makeReservation(customerDTO, tripDTO);
    }

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer customer = customerService.findById(customerId);
        return (customer == null) ? null : dozerMapperService.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer customer = customerService.findByEmail(email);
        return (customer == null) ? null : dozerMapperService.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return dozerMapperService.mapTo(customerService.findAll(), CustomerDTO.class);
    }
}