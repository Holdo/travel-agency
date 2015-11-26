
package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Administrator;
import java.util.List;

/**
 *
 * @author Branislav Bohumel
 */
public interface AdministratorService {
    
    void create(Administrator administrator);
    void update(Administrator administrator);
    void delete(Administrator administrator);
    Administrator findById(Long id);
    List<Administrator> listAll();
    
}