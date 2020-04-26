package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.exception.DBException;

/**
 * Extends base interface {@link AbstractDao}
 * entity in data source
 */
public interface BlockUnBlockUserDao {

    /**
     * Returns status blocked.
     * @param userID user id who status you need
     * @throws DBException if occurred some exception with
     *      *         data source or connection with it
     */
    void blockUser(int userID) throws DBException;

    /**
     * Returns status unblocked.
     * @param userID user id who status you need
     * @throws DBException if occurred some exception with
     *      *         data source or connection with it
     */
    void unblockUser(int userID) throws DBException;
}
