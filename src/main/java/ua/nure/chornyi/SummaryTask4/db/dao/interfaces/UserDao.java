package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link User}
 * entity in data source
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Returns a user with the given login.
     * @param login user login.
     * @return User user.
     * @throws DBException
     */
    User findUserByLogin(String login) throws DBException;

    /**
     * Finds login in table.
     * @param login user login.
     * @return true if there is the login in the table, false if not.
     * @throws DBException
     */
    boolean checkLogin(String login) throws DBException;

    /**
     * Returns all subscribers.
     * @param roleId
     * @return list of subscribers.
     * @throws DBException
     */
    List<User> findSubscribers(int roleId) throws DBException;

    /**
     * Extracts a user entity from the result set.
     * @param resultSet Result set from which a user entity will be extracted.
     * @return User user
     * @throws DBException
     * @throws SQLException
     */
    User extractUser(ResultSet resultSet) throws DBException, SQLException;
}
