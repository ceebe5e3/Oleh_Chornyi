package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.UserDao;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.UserService;

import java.util.List;

/**
 * {@link UserService} implementation for work with MySQL database
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Returns a user with the given login.
     * @param login user login.
     * @return  User user.
     */
    @Override
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    /**
     * Returns all subscribers.
     * @param roleId
     * @return list of subscribers
     */
    @Override
    public List<User> findSubscribers(int roleId) {
        return userDao.findSubscribers(roleId);
    }

    /**
     * Create a new user in data source.
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public void remove(int id) {
        userDao.delete(id);
    }

    /**
     * Updates user in data source
     * @param user
     * @return user
     */
    @Override
    public User update(User user) {
        userDao.update(user);
        return user;
    }
}
