package cz.fi.muni.pa165.travelagency.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Holic
 */
@Service
public class DozerMapperServiceImpl implements DozerMapperService {

    private Mapper dozer = DozerBeanMapperSingletonWrapper.getInstance();
    
    @Override
    public Mapper getMapper() {
        return dozer;
    }
    
    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }
    
    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }
}