package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import java.util.Collection;

/**
 *
 * @author Michal Holic
 */
public interface CustomerFacade extends UserFacade {
    long makeReservation(CustomerDTO customerDTO, TripDTO tripDTO);
    CustomerDTO findCustomerById(Long userId);
    CustomerDTO findCustomerByEmail(String email);
    Collection<CustomerDTO> getAllCustomers();
}
