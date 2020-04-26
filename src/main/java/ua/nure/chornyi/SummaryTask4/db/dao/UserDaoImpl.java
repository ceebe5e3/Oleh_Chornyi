package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.UserDao;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;
import ua.nure.chornyi.SummaryTask4.web.utils.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link UserDao} implementation, provided DAO-logic for {@link User}
 * entity with MySQL DataBase
 */
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String CHECK_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SELECT_USER = "SELECT * FROM users WHERE role_id=?";
    private static final String INSERT_USER = "INSERT INTO users (full_name, date_of_birth, address, email, login, password, role_id) "
            + "VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_LOGIN_AND_PASSWORD = "UPDATE users SET login=?, password=? WHERE id=?";
    private static final String INSERT_ACCOUNT = "INSERT INTO account VALUES (DEFAULT, ?, DEFAULT, DEFAULT, DEFAULT)";

    /**
     * Returns a user with the given login.
     * @param login user login.
     * @return User user.
     */
    @Override
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    /**
     * Finds login in table.
     * @param login user login.
     * @return true if there is the login in the table, false if not.
     * @throws DBException
     */
    @Override
    public boolean checkLogin(String login) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LOGIN, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LOGIN, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return false;
    }

    /**
     * Updates user in data source
     * @param user
     * @throws DBException
     */
    @Override
    public void update(User user) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_UPDATE_USER, ex);
            throw new DBException(Messages.ERROR_CANNOT_UPDATE_USER, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Create a new user in data source.
     * @param user
     * @return user
     */
    @Override
    public User create(User user) {
        PreparedStatement preparedStatementAdd = null;
        PreparedStatement preparedStatementSelect = null;
        PreparedStatement preparedStatementInsertAcc = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatementAdd = connection.prepareStatement(INSERT_USER);
            preparedStatementAdd.setString(1, user.getFullName());
            preparedStatementAdd.setDate(2, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatementAdd.setString(3, user.getAddress());
            preparedStatementAdd.setString(4, user.getEmail());
            preparedStatementAdd.setString(5, user.getLogin());
            preparedStatementAdd.setString(6, user.getPassword());
            preparedStatementAdd.setInt(7, user.getRoleId());
            preparedStatementAdd.executeUpdate();

            preparedStatementSelect = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatementSelect.setString(1, user.getLogin());
            resultSet = preparedStatementSelect.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString(Fields.ENTITY_ID));
            }
            preparedStatementInsertAcc = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatementInsertAcc.setInt(1, id);
            preparedStatementInsertAcc.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_ADD_USER, ex);
            throw new DBException(Messages.ERROR_CANNOT_ADD_USER, ex);
        } finally {
            close(preparedStatementSelect);
            close(connection, preparedStatementAdd, resultSet);
        }
        return user;
    }

    /**
     * Extracts a user entity from the result set.
     * @param resultSet Result set from which a user entity will be extracted.
     * @return User user
     * @throws SQLException
     */
    @Override
    public User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Fields.ENTITY_ID));
        user.setFullName(resultSet.getString(Fields.USER_FULL_NAME));
        user.setDateOfBirth(resultSet.getDate(Fields.USER_DATE_OF_BIRTH));
        user.setAddress(resultSet.getString(Fields.USER_ADDRESS));
        user.setEmail(resultSet.getString(Fields.USER_EMAIL));
        user.setLogin(resultSet.getString(Fields.USER_LOGIN));
        user.setPassword(Encryption.encrypt(resultSet.getString(Fields.USER_PASSWORD)));
        user.setRoleId(resultSet.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    /**
     * Returns all subscribers.
     * @param roleId
     * @return list of subscribers.
     * @throws DBException
     */
    @Override
    public List<User> findSubscribers(int roleId) throws DBException {
        List<User> subscribers = new ArrayList<User>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setInt(1, roleId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subscribers.add(extractUser(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_SUBSCRIBERS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_SUBSCRIBERS, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return subscribers;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
