package cz.fi.muni.pa165.travelagency.service;

import cz.fi.muni.pa165.travelagency.dao.ExcursionDao;
import cz.fi.muni.pa165.travelagency.entity.Excursion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Holic
 */
@Service
public class ExcursionServiceImpl implements ExcursionService {
    
    @Autowired
    ExcursionDao excursionDao;

    @Override
    public void create(Excursion excursion) {
        excursionDao.create(excursion);
    }

    @Override
    public void update(Excursion excursion) {
        excursionDao.update(excursion);
    }

    @Override
    public void delete(Excursion excursion) {
        excursionDao.delete(excursion);
    }

    @Override
    public Excursion getById(Long id) {
        return excursionDao.getById(id);
    }

    @Override
    public List<Excursion> getAll() {
        return excursionDao.getAll();
    }
    
}
