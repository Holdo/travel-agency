package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.Administrator;
import java.util.List;

/**
 *
 * 
 * @author Branislav Bohumel
 */
public interface AdministratorDao {
    public void create(Administrator administrator);
    public Administrator findById(Long id);
    public void update(Administrator administrator);
    public void delete(Administrator administrator);
    public List<Administrator> findAll();
    
}
