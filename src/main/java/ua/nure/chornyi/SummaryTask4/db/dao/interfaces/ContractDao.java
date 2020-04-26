package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Contract;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Extends base interface {@link AbstractDao}, adding some special
 * methods for work with {@link Contract}
 * entity in data source
 */
public interface ContractDao extends AbstractDao<Contract> {

    /**
     * Returns list of user contracts
     * @param id user id
     * @return list of user contracts
     * @throws DBException
     */
    List<Contract> findUserContracts(int id) throws DBException;

    /**
     * @param userId user`s id.
     * @param serviceId selected service id.
     * @param tariffId selected tariff id.
     * @param date, date activation.
     * @throws DBException
     */
    void createNewContract(int userId, int serviceId, int tariffId, Date date) throws DBException;

    /**
     * Extracts a contract entity from the result set.
     * @param resultSet Result set from which a contract entity will be extracted.
     * @return Contract contract
     * @throws SQLException
     */
    Contract extractContract(ResultSet resultSet) throws SQLException;
}
