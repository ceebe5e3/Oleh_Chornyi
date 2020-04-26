package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ServiceDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Service;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link ServiceDao} implementation, provided DAO-logic for {@link Service}
 * entity with MySQL DataBase
 */
public class ServiceDaoImpl implements ServiceDao {

    private static final Logger logger = Logger.getLogger(ServiceDaoImpl.class);

    private static final String SELECT_SERVICE = "SELECT * FROM services";

    @Override
    public Service create(Service service) {
        return null;
    }

    @Override
    public void update(Service service) {
    }

    @Override
    public Service getById(int id) {
        return null;
    }

    /**
     * Returns service, which exist in data source
     * @return service
     */
    @Override
    public List<Service> getAll() {
        List<Service> serviceList = new ArrayList<Service>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_SERVICE);
            while (resultSet.next()) {
                serviceList.add(extractService(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICES, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICES, ex);
        } finally {
            close(connection, statement, resultSet);
        }
        return serviceList;
    }

    /**
     * Extracts a service entity from the result set.
     * @param resultSet Result set from which a service entity will be extracted.
     * @return Service service
     * @throws SQLException
     */
    @Override
    public Service extractService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getInt(Fields.ENTITY_ID));
        service.setName(resultSet.getString(Fields.SERVICE_NAME));
        return service;
    }

    @Override
    public void delete(int id) {
    }
}
