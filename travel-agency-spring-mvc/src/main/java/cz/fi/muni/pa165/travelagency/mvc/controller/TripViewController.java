package cz.fi.muni.pa165.travelagency.mvc.controller;


import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.facade.TripFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Diana Vilkolakova
 */
@Controller
public class TripViewController {

    final static Logger log = LoggerFactory.getLogger(TripViewController.class);

    @Autowired
    private TripFacade tripFacade;

    /**
     * Shows trip detail.
     *
     * @param id    trip id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/trip/{id}")
    public String trip(@PathVariable long id, Model model) {
        log.debug("trip({})", id);
        model.addAttribute("trip", tripFacade.getById(id));
        return "trip";
    }
}

