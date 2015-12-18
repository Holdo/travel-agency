package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.ExcursionFacade;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import cz.fi.muni.pa165.travelagency.mvc.forms.TripDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Michal Holic
 */
@Controller
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private ExcursionFacade excursionFacade;

	@Autowired
	private TripFacade tripFacade;

	final static Logger log = LoggerFactory.getLogger(TripController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(Set.class, new CustomCollectionEditor(Set.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element == null) return null;
				Set<ExcursionDTO> excursions = new HashSet<>();
				if (element instanceof String) {
					String id = (String) element;
					excursions.add(excursionFacade.getById(Long.decode(id)));
				} else {
					String[] excursionsArray = (String[]) element;
					for (String id : excursionsArray) {
						excursions.add(excursionFacade.getById(Long.decode(id)));
					}
				}
				return excursions;
			}
		});
		if (binder.getTarget() instanceof TripDTO) {
			binder.addValidators(new TripDTOValidator());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String trip(@PathVariable long id, Model model) {
		model.addAttribute("trip", tripFacade.getById(id));
		return "trip/trip";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newTripForm(Model model) {
		model.addAttribute("tripCreate", new TripDTO());
		return "trip/new";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createTrip(@Valid @ModelAttribute("tripCreate") TripDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "trip/new";
		}
		try {
			tripFacade.create(formBean);
			redirectAttributes.addFlashAttribute("alert_success", "Trip " + formBean.getDestination() + " was created");
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
		}
		return "redirect:" + uriBuilder.path("/index").toUriString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editTripForm(@PathVariable long id, Model model) {
		TripDTO tripDTO = tripFacade.getById(id);
		model.addAttribute("tripEdit", tripDTO);
		model.addAttribute("id", id);
		model.addAttribute("destination", tripDTO.getDestination());
		model.addAttribute("dateFrom", tripDTO.getDateFrom());
		model.addAttribute("dateTo", tripDTO.getDateTo());
		model.addAttribute("numberOfAvailable", tripDTO.getNumberOfAvailable());
		model.addAttribute("price", tripDTO.getPrice());
		return "trip/edit";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateTrip(@PathVariable long id, @Valid @ModelAttribute("tripEdit") TripDTO formBean,
							 UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		TripDTO tripDTO = tripFacade.getById(id);
		if (formBean.getDateFrom() != null) tripDTO.setDateFrom(formBean.getDateFrom());
		if (formBean.getDateTo() != null) tripDTO.setDateTo(formBean.getDateTo());
		if (formBean.getPrice() != null) tripDTO.setPrice(formBean.getPrice());
		if (formBean.getDestination() != null) tripDTO.setDestination(formBean.getDestination());
		if (formBean.getNumberOfAvailable() != null) tripDTO.setNumberOfAvailable(formBean.getNumberOfAvailable());

		try {
			tripFacade.update(tripDTO);
			redirectAttributes.addFlashAttribute("alert_success", "Trip id " + tripDTO.getId() + " was updated.");
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
		}
		return "redirect:" + uriBuilder.path("/index").toUriString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteTrip(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		TripDTO tripDTO = tripFacade.getById(id);
		if (tripDTO.getReservations().size() != 0) {
			redirectAttributes.addFlashAttribute("alert_warning", "Trip id " + id + " was not deleted because there is a reservation to it.");
			return "redirect:" + uriBuilder.path("/index").toUriString();
		}
		if (tripDTO.getExcursions().size() != 0) {
			redirectAttributes.addFlashAttribute("alert_warning", "Trip id " + id + " was not deleted because it still contains excursions.");
			return "redirect:" + uriBuilder.path("/index").toUriString();
		}
		try {
			tripFacade.delete(id);
			redirectAttributes.addFlashAttribute("alert_success", "Trip id " + id + " was deleted.");
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
		}
		return "redirect:" + uriBuilder.path("/index").toUriString();
	}

	@ModelAttribute("excursions")
	public List<ExcursionDTO> excursions() {
		return excursionFacade.getAll();
	}
}
