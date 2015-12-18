package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.facade.ExcursionFacade;
import cz.fi.muni.pa165.travelagency.mvc.forms.ExcursionDTOValidator;
import cz.fi.muni.pa165.travelagency.mvc.util.CustomDurationEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import javax.validation.Valid;

import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Branislav Bohumel
 */
@Controller
public class ExcursionController {

	@Autowired
	private ExcursionFacade excursionFacade;

	final static Logger log = LoggerFactory.getLogger(MainController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Duration.class, new CustomDurationEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		if (binder.getTarget() instanceof ExcursionDTO) {
            binder.addValidators(new ExcursionDTOValidator());
        }
	}

	@RequestMapping(value = "excursion/list", method = RequestMethod.GET)
	public String excursionListPage(Model model) {
		//get all excursions
		List<ExcursionDTO> allExcursions = excursionFacade.getAll();
		model.addAttribute("excursions", allExcursions);
		return "excursion/list";
	}

	@RequestMapping(value = "excursion/{id}", method = RequestMethod.GET)
	public String excursionViewPage(Model model, @PathVariable("id") int id) {
		//get the excursion
		ExcursionDTO excursion;
		if (id > excursionFacade.getAll().size()) {
			return "excursion/view";
		}
		excursion = excursionFacade.getById(Long.valueOf(id));
		model.addAttribute("excursion", excursion);
		return "excursion/view";
	}

	@RequestMapping(value = "excursion/edit/{id}", method = RequestMethod.GET)
	public String excursionEditPage(Model model, @PathVariable("id") long id) {
		//get the excursion
		ExcursionDTO excursion;
		if (id == 0) {
			model.addAttribute("excursionCreate", new ExcursionDTO());
			return "excursion/edit";
		}
		excursion = excursionFacade.getById(id);
		model.addAttribute("id", id);
		model.addAttribute("excursion", excursion);
		return "excursion/edit";
	}

	@RequestMapping(value = "excursion/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		ExcursionDTO excursion = excursionFacade.getById(id);
		excursionFacade.delete(id);
		redirectAttributes.addFlashAttribute("alert_success", "Excursion \"" + excursion.getDestination() + "\" was deleted.");
		return "redirect:" + uriBuilder.path("/excursion/list").toUriString();
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "excursion/new", method = RequestMethod.POST)
	public String excursionNew(@Valid @ModelAttribute("excursionCreate") ExcursionDTO formBean, BindingResult bindingResult,
							   Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "excursion/edit";
		}
		try {
			excursionFacade.create(formBean);
			redirectAttributes.addFlashAttribute("alert_success", "Excursion " + formBean.getDestination() + " was created");
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
		}
		return "redirect:" + uriBuilder.path("/excursion/list").toUriString();
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "excursion/update/{id}", method = RequestMethod.POST)
	public String excursionUpdate(@Valid @ModelAttribute("excursionCreate") ExcursionDTO formBean, BindingResult bindingResult,
								  @PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes,
								   UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "excursion/edit";
		}
		ExcursionDTO excursionDTO = excursionFacade.getById(id);
		if (formBean.getDestination() != null) excursionDTO.setDestination(formBean.getDestination());
		if (formBean.getPrice() != null) excursionDTO.setPrice(formBean.getPrice());
		if (formBean.getDate() != null) excursionDTO.setDate(formBean.getDate());
		if (formBean.getDuration() != null) excursionDTO.setDuration(formBean.getDuration());

		try {
			excursionFacade.update(excursionDTO);
			redirectAttributes.addFlashAttribute("alert_success", "Excursion " + formBean.getDestination() + " was updated");
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
		}
		return "redirect:" + uriBuilder.path("/excursion/list").toUriString();
	}

}