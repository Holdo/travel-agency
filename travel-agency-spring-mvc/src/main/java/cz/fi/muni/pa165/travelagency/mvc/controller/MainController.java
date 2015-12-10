package cz.fi.muni.pa165.travelagency.mvc.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Michal Holic
 */
@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(Model model) {
		model.addAttribute("title", "Spring Security Login Form - Database Authentication");
		model.addAttribute("message", "This is default page!");
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "logout", required = false) String logout,
						Model model) {
		if (error != null) model.addAttribute("error", "Invalid username and password!");
		if (logout != null) model.addAttribute("msg", "You've been logged out successfully.");
		return "login";
	}

	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
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