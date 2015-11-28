package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.ExcursionDao;
import cz.fi.muni.pa165.travelagency.entity.Excursion;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Branislav Bohumel
 */
@ContextConfiguration(locations = "/SpringXMLConfig.xml")
public class ExcursionServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    @Mock
    private ExcursionDao excursionDao;
    
    @Autowired
    @InjectMocks
    private ExcursionService excursionService;
    
    private Excursion excursion = new Excursion();
    private Excursion excursion2 = new Excursion();
    private List<Excursion> excursionList = new ArrayList<>(); 
    
    String date1 = "1999-09-27";  
    String date2 = "1999-10-11";    
    
    @BeforeMethod
    public void setup(){
        MockitoAnnotations.initMocks(this);
              
        excursion.setDate(Date.valueOf(date1));
        excursion.setDuration(Duration.ofHours(2));
        excursion.setDestination("Pentagon");
        excursion.setPrice(new BigDecimal("20"));
        
        excursion2.setDate(Date.valueOf(date2));
        excursion2.setDuration(Duration.ofHours(3));
        excursion2.setDestination("Petrohrad");
        excursion2.setPrice(new BigDecimal("50"));                              
    }
    
    
    @Test
    public void createTest(){     
        doNothing().when(excursionDao).create(any(Excursion.class));        
        excursionService.create(excursion);
        verify(excursionDao).create(excursion); 
    }
    
    @Test
    public void updateTest(){            
        doNothing().when(excursionDao).update(any(Excursion.class));        
        excursion.setDestination("Prievidza");
        excursionService.update(excursion);
        verify(excursionDao).update(excursion);
    }
    
    @Test
    public void deleteTest(){
        doNothing().when(excursionDao).delete(any(Excursion.class));        
        excursionService.delete(excursion);
        verify(excursionDao).delete(excursion);        
    }
    
    @Test
    public void getByIdTest(){
        Long id = excursion.getId();
        when(excursionDao.getById(any(Long.class))).thenReturn(excursion);        
        excursionService.getById(id);
        verify(excursionDao).getById(id);
    }
    
    @Test
    public void getAllTest(){     
        when(excursionDao.getAll()).thenReturn(excursionList);
        List<Excursion> excursionsDto = excursionService.getAll();
        int originalSize = excursionsDto.size();
        excursionList.add(excursion2);   
        int newSize = excursionsDto.size();    
        assertEquals(originalSize, newSize - 1);
        verify(excursionDao).getAll();
    }
}