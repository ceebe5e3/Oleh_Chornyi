package ua.nure.chornyi.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBManager.
 */
public class DBManager {

    private static final Logger logger = Logger.getLogger(DBManager.class);

    private static DBManager instance;
    private DataSource dataSource;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/provider");
            logger.trace("Data source ==> " + dataSource);
        } catch (NamingException ex) {
            logger.error(Messages.ERROR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public Connection getConnection() throws DBException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            logger.error(Messages.ERROR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return connection;
    }
}
