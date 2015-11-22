package cz.fi.muni.pa165.travelagency.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Michal Holic
 */
public interface DozerMapperService {
    public Mapper getMapper();
    public <T> T mapTo(Object u, Class<T> mapToClass);
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
}
