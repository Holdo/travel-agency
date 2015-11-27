package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Excursion;
import java.util.Collection;

/**
 *
 * @author Michal Holic
 */
public interface ExcursionService {

    public void create(Excursion excursion);
    public void update(Excursion excursion);
    public void delete(Excursion excursion);
    public Excursion findById(Long id);
    public Collection<Excursion> findAll();

}
