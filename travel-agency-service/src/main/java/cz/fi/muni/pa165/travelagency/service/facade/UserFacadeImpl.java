package cz.fi.muni.pa165.travelagency.service.facade;

import cz.fi.muni.pa165.travelagency.dto.UserDTO;
import cz.fi.muni.pa165.travelagency.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Holic
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Override
    public void register(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
