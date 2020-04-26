package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link ServiceTariffs}
 * entity in data source
 */
public interface ServiceTariffsDao extends AbstractDao<ServiceTariffs> {

    /**
     * Returns services and tariffs with the given id.
     * @param tariffID tariff id
     * @return ServiceTariffs serviceTariffs
     * @throws DBException
     */
    List<ServiceTariffs> findServiceTariffsByTariffID(int tariffID) throws DBException;

    /**
     * Edits tariff.
     * @param serviceTariffs serviceTariffs
     * @throws DBException
     */
    void editTariff(ServiceTariffs serviceTariffs) throws DBException;

    /**
     * Returns all services with tariffs.
     * @return List of serviceTariffs entities.
     * @throws DBException
     */
    List<ServiceTariffs> findServicesAndTariffs() throws DBException;

    /**
     * Extracts a ServicesTariffs entity from the result set.
     * @param resultSet Result set from which a ServiceTariffs entity will be
     * 	 *            extracted.
     * @return ServiceTariffs serviceTariffs
     * @throws DBException
     * @throws SQLException
     */
    ServiceTariffs extractServiceTariffs(ResultSet resultSet) throws DBException, SQLException;

}
