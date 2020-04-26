package ua.nure.chornyi.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBUtil.
 */
public class DBUtil {

    private static final Logger logger = Logger.getLogger(DBUtil.class);

    /**
     * Closes a connection
     * @param connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(Messages.ERROR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                logger.error(Messages.ERROR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                logger.error(Messages.ERROR_CANNOT_CLOSE_RESULT_SET, ex);
            }
        }
    }

    /**
     * Closes a connection resource
     * @param connection
     * Closes a statement resource
     * @param statement
     * Closes a result set resource
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }


    /**
     * Rollbacks a connection
     * @param connection
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Cannot rollback transaction", ex);
            }
        }
    }

}
