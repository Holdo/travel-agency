/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.travelagency.facade;

import cz.fi.muni.pa165.travelagency.dto.UserDTO;

/**
 *
 * @author Michal Holic
 */
public interface UserFacade {
    void register(UserDTO userDTO);
    boolean authenticate(UserDTO userDTO);
}
