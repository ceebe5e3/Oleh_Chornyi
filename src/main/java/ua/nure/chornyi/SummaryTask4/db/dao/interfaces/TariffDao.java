package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link Tariff}
 * entity in data source
 */
public interface TariffDao extends AbstractDao<Tariff> {

    /**
     * Insert tariff
     * @param tariff tariff name
     * @throws DBException
     */
    void insertTariff(String tariff) throws DBException;

    /**
     * Returns tariff id with the given name.
     * @param tariff tariff name
     * @return tariff id
     * @throws DBException
     */
    int getTariffId(String tariff) throws DBException;

    /**
     * Insert tariff into 'tariffs' and 'service_tariffs' tables.
     * @param tariff tariff name.
     * @param typeOfService service id.
     * @param price price tariff.
     * @param description
     * @throws DBException
     */
    void insertTariffWithService(String tariff, int typeOfService, double price, String description) throws DBException;

    /**
     * Extracts a tariff entity from the result set.
     * @param resultSet Result set from which a tariff entity will be extracted.
     * @return Tariff tariff
     * @throws DBException
     * @throws SQLException
     */
    Tariff extractTariff(ResultSet resultSet) throws DBException, SQLException;
}
