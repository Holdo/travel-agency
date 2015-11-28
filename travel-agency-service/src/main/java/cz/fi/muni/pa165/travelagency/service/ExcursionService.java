package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.entity.Excursion;
import java.util.List;

/**
 *
 * @author Michal Holic
 */
public interface ExcursionService {

    void create(Excursion excursion);
    void update(Excursion excursion);
    void delete(Excursion excursion);
    Excursion getById(Long id);
    List<Excursion> getAll();

}
