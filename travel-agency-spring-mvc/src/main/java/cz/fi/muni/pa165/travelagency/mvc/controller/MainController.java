package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michal Holic
 */
@Controller
public class MainController {

	@Autowired
	private TripFacade tripFacade;

	final static Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String defaultPage(Model model) {
		//get all trips
		List<TripDTO> allTrips = tripFacade.getAll();
		model.addAttribute("trips", allTrips);

		//for each trip, get excursions, remember that as a map from trip ids to excursions
		Map<Long, Collection<ExcursionDTO>> tripsToExcursionsMap = new HashMap<>();
		for (TripDTO tripDTO : allTrips) {
			tripsToExcursionsMap.put(tripDTO.getId(), tripDTO.getExcursions());
		}
		model.addAttribute("trip2excursions", tripsToExcursionsMap);

		//get logged in username
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", auth.getName());
		log.info("Roles:");
		for (GrantedAuthority authority : auth.getAuthorities()) {
			log.info(String.valueOf(auth.getAuthorities().size()));
			log.info(authority.toString());
			log.info(authority.getAuthority());
		}

		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "logout", required = false) String logout,
						Model model) {
		if (error != null) model.addAttribute("error", "Invalid username and password!");
		if (logout != null) model.addAttribute("msg", "You've been logged out successfully.");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam(value = "username", required = true) String username,
						   @RequestParam(value = "password", required = true) String password,
						   Model model) {
		return "login";
	}

	//for 403 access denied page if user is disabled
	@RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST})
	public String accesssDenied(Model model) {
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
		}
		return "403";
	}
}