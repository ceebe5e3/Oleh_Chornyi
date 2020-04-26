package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link Service}
 * entity in data source
 */
public interface ServiceDao extends AbstractDao<Service> {

    /**
     * Extracts a service entity from the result set.
     * @param resultSet Result set from which a service entity will be extracted.
     * @return Service service
     * @throws SQLException
     */
    Service extractService(ResultSet resultSet) throws SQLException;
}
