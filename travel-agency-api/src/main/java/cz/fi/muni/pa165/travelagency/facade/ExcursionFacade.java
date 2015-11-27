
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import java.util.List;

/**
 *
 * @author Julius Stassik
 */
public interface ExcursionFacade {
    
    /**
     * Create excursion
     * 
     * @param excursion - excursion which will be created 
     */
    public void create(ExcursionDTO excursion);
    
    /**
     * Delete excursion specified by its id
     * 
     * @param id - id of deleted excursion
     */
    public void delete(Long id);
    
    /**
     * Update excursion
     * 
     * @param excursion - excursion which will be updated
     */
    public void update(ExcursionDTO excursion);
    
    /**
     * Find excursion by its id
     * 
     * @param id - id of excursion which will be found
     * @return ExcursionDTO - desired excursion or null when there is no 
     *                         excursion with specified id
     */
    public ExcursionDTO getById(Long id);
    
    /**
     * find all excursions
     * 
     * @return list of all excursions
     */
    public List<ExcursionDTO> getAll();

      
}
