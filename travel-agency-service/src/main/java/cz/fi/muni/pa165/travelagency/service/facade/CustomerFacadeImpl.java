package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.entity.Customer;
import cz.fi.muni.pa165.travelagency.entity.Trip;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.service.CustomerService;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;
import cz.fi.muni.pa165.travelagency.service.ReservationService;
import cz.fi.muni.pa165.travelagency.service.TripService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Holic
 */
@Service
@Transactional
public class CustomerFacadeImpl extends UserFacadeImpl implements CustomerFacade {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TripService tripService;

    @Autowired
    private DozerMapperService dms;

    @Override
    public void create(CustomerDTO customerDTO) {
        customerService.create(dms.mapTo(customerDTO, Customer.class));
    }

    @Override
    public long makeReservation(CustomerDTO customerDTO, TripDTO tripDTO) {
        Customer customer = customerService.getById(customerDTO.getId());
        Trip trip = tripService.getById(tripDTO.getId());
        return customerService.makeReservation(customer, trip);
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        customerService.update(dms.mapTo(customerDTO, Customer.class));
    }

    @Override
    public void delete(long id) {
        customerService.delete(customerService.getById(id));
    }

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer customer = customerService.getById(customerId);
        return (customer == null) ? null : dms.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer customer = customerService.getByEmail(email);
        return (customer == null) ? null : dms.mapTo(customer, CustomerDTO.class);
    }
    
    @Override
    public CustomerDTO findCustomerByUsername(String username){
        Customer customer = customerService.getByUsername(username);
        return (customer == null) ? null : dms.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return dms.mapTo(customerService.getAll(), CustomerDTO.class);
    }
}