package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link Account}
 * entity in data source
 */
public interface AccountDao extends AbstractDao<Account> {

    /**
     * Returns amount of payments by user id and it's destination
     * @param userId user id who payments you want
     * @param sum of entries
     * @throws DBException if occurred some exception with data
     *      *         source or connection with it
     *
     */
    void accountPayment(int userId, double sum) throws DBException;

    /**
     * Extracts a account entity from the result set.
     * @param resultSet  Result set from which a account entity will be extracted.
     * @return Account account
     * @throws DBException if occurred some exception with data
     *      *      *         source or connection with it
     * @throws SQLException if occurred some exception with data
     *      *      *         source or connection with it
     */
    Account extractAccount(ResultSet resultSet) throws DBException, SQLException;
}
