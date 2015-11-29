package cz.fi.muni.pa165.travelagency.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 * Dozer mapper service
 * 
 * @author Michal Holic
 */
public interface DozerMapperService {
    Mapper getMapper();
    
    /*
     * Maps classes
     */
    <T> T mapTo(Object u, Class<T> mapToClass);
    
    /*
     * Maps collections
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
}