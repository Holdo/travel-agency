package cz.fi.muni.pa165.travelagency;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class used to play around with project
 * 
 * @author 
 */
public class Main {
    
    @PersistenceContext
    private EntityManager em;
    
    public static void main(String ... args) {
        //This context is not used anymore
        //ApplicationContext derbySpringAnnotationContext = new AnnotationConfigApplicationContext(EmbeddedDatabaseSpringConfig.class);
        ApplicationContext springXMLContext = new ClassPathXmlApplicationContext("/SpringXMLConfig.xml");
    }
}
