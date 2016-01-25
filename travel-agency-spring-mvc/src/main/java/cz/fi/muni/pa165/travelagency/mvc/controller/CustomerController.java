/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.travelagency.mvc.controller;

import cz.fi.muni.pa165.travelagency.dto.CustomerDTO;
import cz.fi.muni.pa165.travelagency.entity.UserRole;
import cz.fi.muni.pa165.travelagency.facade.CustomerFacade;
import cz.fi.muni.pa165.travelagency.mvc.exceptions.NotFoundException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Diana Vilkolakova
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    final static Logger log = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerFacade customerFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerFacade.getAllCustomers());
        return "customer/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String customerViewPage(Model model, @PathVariable("id") int id) {
        //get the customer
        CustomerDTO customerDTO = customerFacade.findCustomerById((long) id);
        if (customerDTO == null) {
            throw new NotFoundException();
        }
        model.addAttribute("customer", customerDTO);
        return "customer/view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String customerEditPage(Model model, @PathVariable("id") long id) {
        //get the customer
        CustomerDTO customerDTO;
        if (id == 0 && !model.containsAttribute("customerEdit")) {
            model.addAttribute("customerCreate", new CustomerDTO());
            return "customer/edit";
        }
        if (!model.containsAttribute("customerEdit")) {
            customerDTO = customerFacade.findCustomerById((long) id);
            if (customerDTO == null) {
                throw new NotFoundException();
            }
            model.addAttribute("id", id);
            model.addAttribute("customerEdit", customerDTO);
        }
        return "customer/edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);
        if (customerDTO == null) {
            throw new NotFoundException();
        }
        if (!customerDTO.getReservations().isEmpty()) {
            redirectAttributes.addFlashAttribute("alert_warning", "Customer \"" + customerDTO.getUsername() + "\" has reservations and cannot be deleted");
            return "redirect:" + uriBuilder.path("/customer/list").toUriString();
        }
        customerFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Customer \"" + customerDTO.getUsername() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/customer/list").toUriString();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String customerNew(@Valid @ModelAttribute("customerCreate") CustomerDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "customer/edit";
        }
        try {
            customerFacade.create(formBean);
            redirectAttributes.addFlashAttribute("alert_success", "Customer " + formBean.getUsername() + " was created");
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
        }
        return "redirect:" + uriBuilder.path("/customer/list").toUriString();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String customerUpdate(@Valid @ModelAttribute("customerEdit") CustomerDTO formBean, BindingResult bindingResult,
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
            return customerEditPage(model, id);
        }
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);
        if (customerDTO == null) {
            throw new NotFoundException();
        }
        if (formBean.getUsername() != null) {
            customerDTO.setUsername(formBean.getUsername());
        }
        if (formBean.getFirstName() != null) {
            customerDTO.setFirstName(formBean.getFirstName());
        }
        if (formBean.getLastName() != null) {
            customerDTO.setLastName(formBean.getLastName());
        }
        if (formBean.getEmail() != null) {
            customerDTO.setEmail(formBean.getEmail());
        }
        if (formBean.getPassword() != null) {
            customerDTO.setPassword(formBean.getPassword());
        }
        if (formBean.getRole() != null) {
            customerDTO.setRole(UserRole.ROLE_USER.toString());
        }

        try {
            customerFacade.update(customerDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Customer " + formBean.getUsername() + " was updated");
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("alert_warning", e.getMessage());
        }
        return "redirect:" + uriBuilder.path("/customer/list").toUriString();
    }
}
