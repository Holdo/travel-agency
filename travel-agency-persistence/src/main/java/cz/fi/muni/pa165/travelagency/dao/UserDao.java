package cz.fi.muni.pa165.travelagency.dao;

import cz.fi.muni.pa165.travelagency.entity.User;

/**
 * UserDao interface.
 * 
 * @author Michal Holic
 */
public interface UserDao {
    public <T extends User> void createUser(T user);
}
