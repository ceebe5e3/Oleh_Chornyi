package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.BlockUnBlockUserDao;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.*;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link BlockUnBlockUserDao} implementation}
 * entity with MySQL DataBase
 */
public class BlockUnBlockUserDaoImpl implements BlockUnBlockUserDao {

    private static final Logger logger = Logger.getLogger(BlockUnBlockUserDaoImpl.class);

    private static final String UNBLOCK_USER = "UPDATE account SET is_blocked_by_admin='UNBLOCKED' WHERE user_id=?";
    private static final String BLOCK_USER = "UPDATE account SET is_blocked_by_admin='BLOCKED' WHERE user_id=?";

    /**
     * Returns status blocked.
     * @param userID user id who status you need
     * @throws DBException
     */
    @Override
    public void blockUser(int userID) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(BLOCK_USER);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_BLOCK_USER, ex);
            throw new DBException(Messages.ERROR_CANNOT_BLOCK_USER, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Returns status unblocked.
     * @param userID user id who status you need
     * @throws DBException
     */
    @Override
    public void unblockUser(int userID) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UNBLOCK_USER);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_UNBLOCK_USER, ex);
            throw new DBException(Messages.ERROR_CANNOT_UNBLOCK_USER, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
}
