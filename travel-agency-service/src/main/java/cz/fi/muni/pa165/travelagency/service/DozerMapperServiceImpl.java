package cz.fi.muni.pa165.travelagency.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Holic
 */
@Service
public class DozerMapperServiceImpl implements DozerMapperService {

    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
    
    private Mapper dozer;
    
    /*{
        reader.loadBeanDefinitions(new ClassPathResource("/SpringXMLConfig.xml"));
        dozer = (Mapper) factory.getBean("mapper");
    }*/
    
    //private ApplicationContext springXMLContext = new ClassPathXmlApplicationContext("/SpringXMLConfig.xml");
    //private Mapper dozer = (Mapper) springXMLContext.getBean("mapper");

    public DozerMapperServiceImpl() {
        reader.loadBeanDefinitions(new ClassPathResource("SpringXMLConfig.xml"));
        dozer = (Mapper) factory.getBean("mapper");
    }
    
    @Override
    public Mapper getMapper(){
    	return dozer;
    }
    
    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return dozer.map(u,mapToClass);
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