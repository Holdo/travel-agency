/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.ExcursionDTO;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import cz.fi.muni.pa165.travelagency.facade.ExcursionFacade;
import cz.fi.muni.pa165.travelagency.service.DozerMapperService;
import cz.fi.muni.pa165.travelagency.service.ExcursionService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

/**
 *
 * @author Julius Stassik
 */

@Service
@Transactional
public class ExcursionFacadeImpl implements ExcursionFacade {

    @Inject
    private ExcursionService excursionService;
    
    @Inject
    private DozerMapperService dozerMapperService;
    
    @Override
    public void createExcursion(ExcursionDTO excursion) {
        if(excursion == null){
            throw new IllegalArgumentException("param excursion is null");
        } else if (excursionService.findById(excursion.getId()) == null){
            throw new IllegalArgumentException("desired excursion doesnt exist");
        }
        
        Excursion exc = dozerMapperService.mapTo(excursion, Excursion.class);
        excursionService.createExcursion(exc);
    }

    @Override
    public void deleteExcursion(Long id) {
        if(id == null){
            throw new IllegalArgumentException("param id is null");
        } else if (excursionService.findById(id) == null){
            throw new IllegalArgumentException("desired excursion doesnt exist");
        }
        
        excursionService.deleteExcursion(excursionService.findById(id));
        
    }

    @Override
    public void updateExcursion(ExcursionDTO excursion) {
        if(excursion == null){
            throw new IllegalArgumentException("param excursion is null");
        } else if (excursionService.findById(excursion.getId()) == null){
            throw new IllegalArgumentException("desired excursion doesnt exist");
        }
        
        Excursion mappedExcursion = dozerMapperService.mapTo(excursion, Excursion.class);
        excursionService.updateExcursion(mappedExcursion);
    }

    @Override
    public ExcursionDTO getExcursionById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("param id is null");
        } else if (excursionService.findById(id) == null){
            throw new IllegalArgumentException("desired excursion doesnt exist");
        }
        
        return dozerMapperService.mapTo(excursionService.findById(id), ExcursionDTO.class);
    }

    @Override
    public List<ExcursionDTO> getAllExcursions() {
        return dozerMapperService.mapTo(excursionService.findAll(), ExcursionDTO.class);
    }
    
}
