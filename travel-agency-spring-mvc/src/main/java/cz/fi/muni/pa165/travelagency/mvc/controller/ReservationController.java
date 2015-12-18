package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.ReservationDTO;
import cz.fi.muni.pa165.travelagency.facade.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
                
        ReservationDTO reservationDTO;
        try{
            reservationDTO = reservationFacade.getById(id);
         
            if(reservationDTO == null){
                redirectAttributes.addFlashAttribute("alert_danger", "Reservation with id "
                        + id + " does not exist!");
                return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
            }
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("alert_danger", "Reservation with id "
                       + id + " does not exist!");
            return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
        }
        
        reservationFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Reservation with "
                    + id + " was deleted!");
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }
        
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list (Model model){
        model.addAttribute("reservations", reservationFacade.getAll());        
        return "reservation/list";
    }
    
    
    
    
    
}
