package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.AccountDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Status;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link AccountDao} implementation, provided DAO-logic for {@link Account}
 * entity with MySQL DataBase
 */
public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);

    private static final String SELECT_USER_ACCOUNT = "SELECT account.id AS accountID, user_id, money, is_blocked, is_blocked_by_admin, users.id, full_name, email, date_of_birth, address, login, password, role_id "
            + "FROM account JOIN users ON users.id=account.user_id "
            + "WHERE user_id=?";
    private static final String ACCOUNT_PAYMENT = "UPDATE account SET money=(money+?) WHERE user_id=?";

    @Override
    public Account create(Account account) {
        return null;
    }

    @Override
    public void update(Account account) {
    }

    /**
     * Returns accounts, which exist in data source
     * @param id, parameter to specify selection
     * @return Account account
     */
    @Override
    public Account getById(int id) {
        Account account = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_ACCOUNT);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = extractAccount(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_ACCOUNT, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_ACCOUNT, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return account;
    }

    /**
     * Extracts a account entity from the result set.
     * @param resultSet Result set from which a account entity will be extracted.
     * @return Account account
     * @throws SQLException
     */
    @Override
    public Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt(Fields.ACCOUNT_ID));
        User user = new UserDaoImpl().extractUser(resultSet);
        account.setUser(user);
        account.setMoney(resultSet.getDouble(Fields.ACCOUNT_MONEY));
        account.setIsBlocked(Status.valueOf(resultSet.getString(Fields.ACCOUNT_IS_BLOCKED)));
        account.setIsBlockedByAdmin(Status.valueOf(resultSet.getString(Fields.ACCOUNT_IS_BLOCKED_BY_ADMIN)));
        return account;
    }


    /**
     * Returns amount of payments by user id and it's destination
     * @param userId user id who payments you want
     * @param sum    of entries
     * @throws DBException
     */
    @Override
    public void accountPayment(int userId, double sum) throws DBException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ACCOUNT_PAYMENT);
            preparedStatement.setDouble(1, sum);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_PAYMENT_ACCOUNT, ex);
            throw new DBException(Messages.ERROR_CANNOT_PAYMENT_ACCOUNT, ex);
        } finally {
            close(connection, preparedStatement);
        }
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
