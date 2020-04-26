package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ContractDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Contract;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link ContractDao} implementation, provided DAO-logic for {@link Contract}
 * entity with MySQL DataBase
 */
public class ContractDaoImpl implements ContractDao {

    private static final Logger logger = Logger.getLogger(ContractDaoImpl.class);

    private static final String SELECT_CONTRACT = "SELECT contracts.id AS contractID, contracts.user_id, service_tariffs.id AS stID, services.name AS services, "
            + "services.id AS servicesID, tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, "
            + "service_tariffs.description, contracts.date, users.id, full_name, email, date_of_birth, address, login, password, role_id  FROM contracts "
            + "JOIN users ON users.id=contracts.user_id "
            + "JOIN service_tariffs ON service_tariffs.id=service_tariff_id "
            + "JOIN services ON services.id=service_tariffs.service_id "
            + "JOIN tariffs ON tariffs.id= service_tariffs.tariff_id";

    private static final String SELECT_USER_CONTRACT = "SELECT contracts.id AS contractID, contracts.user_id, service_tariffs.id AS stID, services.name AS services, "
            + "services.id AS servicesID, tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, "
            + "service_tariffs.description, contracts.date, users.id, full_name, email, date_of_birth, address, login, password, role_id  FROM contracts "
            + "JOIN users ON users.id=contracts.user_id "
            + "JOIN service_tariffs ON service_tariffs.id=service_tariff_id "
            + "JOIN services ON services.id=service_tariffs.service_id "
            + "JOIN tariffs ON tariffs.id= service_tariffs.tariff_id "
            + "WHERE contracts.user_id=?";
    private static final String INSERT_NEW_CONTRACT = "INSERT INTO contracts "
            + "VALUES (DEFAULT, ?, (SELECT id FROM service_tariffs WHERE service_id=? AND tariff_id=?), ?)";

    @Override
    public Contract create(Contract contract) {
        return null;
    }

    @Override
    public void update(Contract contract) {
    }

    @Override
    public Contract getById(int id) {
        return null;
    }

    /**
     * Returns contracts, which exist in data source
     * @return Contract contract
     */
    @Override
    public List<Contract> getAll() {
        List<Contract> contractList = new ArrayList<Contract>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_CONTRACT);
            while (resultSet.next()) {
                contractList.add(extractContract(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_CONTRACTS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_CONTRACTS, ex);
        } finally {
            close(connection, statement, resultSet);
        }
        return contractList;
    }

    /**
     * Returns list of user contracts
     * @param id user id
     * @return list of user contracts
     * @throws DBException
     */
    @Override
    public List<Contract> findUserContracts(int id) throws DBException {
        List<Contract> contracts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_CONTRACT);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contracts.add(extractContract(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_CONTRACT, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_CONTRACT, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return contracts;
    }

    /**
     * Extracts a contract entity from the result set.
     * @param resultSet Result set from which a contract entity will be extracted.
     * @return Contract contract
     * @throws SQLException
     */
    @Override
    public Contract extractContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setId(resultSet.getInt(Fields.CONTRACT_ID));
        User user = new UserDaoImpl().extractUser(resultSet);
        contract.setUser(user);
        contract.setDate(resultSet.getDate(Fields.CONTRACT_DATE));
        ServiceTariffs serviceTariffs = new ServiceTariffsDaoImpl().extractServiceTariffs(resultSet);
        contract.setServiceTariffs(serviceTariffs);
        return contract;
    }

    /**
     * @param userId    user`s id.
     * @param serviceId selected service id.
     * @param tariffId  selected tariff id.
     * @param date,     date activation.
     */
    @Override
    public void createNewContract(int userId, int serviceId, int tariffId, Date date){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_CONTRACT);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, serviceId);
            preparedStatement.setInt(3, tariffId);
            preparedStatement.setDate(4, date);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_INSERT_CONTRACT, ex);
            throw new DBException(Messages.ERROR_CANNOT_INSERT_CONTRACT, ex);
        } finally {
            close(connection, preparedStatement);
        }
    }

    @Override
    public void delete(int id) {

    }
}
