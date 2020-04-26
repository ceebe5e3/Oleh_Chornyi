package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.User;

import java.util.List;

/**
 * Provides work with {@link User} entity and related to it
 */
public interface UserService extends GenericService<User> {

    /**
     * Returns a user with the given login.
     * @param login user login.
     * @return User user
     */
    User findUserByLogin(String login);

    /**
     * Returns all subscribers.
     * @param roleId
     * @return list of subscribers.
     */
    List<User> findSubscribers(int roleId);
}
