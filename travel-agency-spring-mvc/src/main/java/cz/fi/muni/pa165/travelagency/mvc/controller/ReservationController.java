package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.exceptions.TravelAgencyServiceException;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.facade.ReservationFacade;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.mvc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Julius Stassik
 */

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationFacade reservationFacade;
    
    @Autowired
    private CustomerFacade customerFacade;
    
    @Autowired
    private TripFacade tripFacade;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes){
        ReservationDTO reservationDTO;
        try{
            reservationDTO = reservationFacade.getById(id);
         
            if(reservationDTO == null) throw new NotFoundException();
        
            reservationFacade.delete(id);            
            redirectAttributes.addFlashAttribute("alert_success", "Reservation with "
                        + id + " was deleted!");
        } catch (DataAccessException dae){
            redirectAttributes.addFlashAttribute("alert_warning", dae.getMessage());
        }
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }
        
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list (Model model){
        model.addAttribute("reservations", reservationFacade.getAll());        
        return "reservation/list";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public String create(@PathVariable long id, 
        UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        
        CustomerDTO customerDTO = customerFacade.findCustomerByUsername(name);
        TripDTO tripDTO = tripFacade.getById(id);
        
        if (customerDTO == null || tripDTO == null) throw new NotFoundException();        

        long id2 = -1L;
        try{
        id2 = customerFacade.makeReservation(customerDTO, tripDTO);
        } catch(TravelAgencyServiceException e){
            redirectAttributes.addFlashAttribute("alert_danger", "Soryy this trip is full. Please choose anotherone. ");
            return "redirect:" + uriBuilder.path("/index").toUriString();
        } catch (DataAccessException dae){
            redirectAttributes.addFlashAttribute("alert_warning", dae.getMessage());
        }
        
        redirectAttributes.addFlashAttribute("alert_success", "Reservation with id " + id2 + " was created!");
        return "redirect:" + uriBuilder.path("/reservation/view/{id}").buildAndExpand(id = id2).encode().toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model){
        ReservationDTO reservationDTO = reservationFacade.getById(id);
        if (reservationDTO == null) throw new NotFoundException();
        model.addAttribute("reservation", reservationDTO);
        return "reservation/view";
    }
    
    @RequestMapping(value = "/list/{username}", method = RequestMethod.GET)
    public String listOfCustomerReservations(Model model, @PathVariable("username") String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == null ? username != null : !auth.getName().equals(username)) {
            return "/403";
        }
        CustomerDTO customerDTO = customerFacade.findCustomerByUsername(username);
        model.addAttribute("reservations", reservationFacade.getReservations(customerDTO));
        model.addAttribute("username", username);
        return "reservation/listCustomersReservations";
    }
}
