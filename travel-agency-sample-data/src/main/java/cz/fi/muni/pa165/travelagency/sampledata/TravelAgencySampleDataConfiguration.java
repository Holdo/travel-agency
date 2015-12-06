package cz.fi.muni.pa165.travelagency.sampledata;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Michal Holic
 */
@Configuration
@ImportResource(locations = "classpath:SpringXMLConfig.xml")
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class TravelAgencySampleDataConfiguration {
    
    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;
    
    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}
