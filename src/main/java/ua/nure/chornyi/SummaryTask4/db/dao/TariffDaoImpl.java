package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.TariffDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.*;

/**
 * {@link TariffDao} implementation, provided DAO-logic for {@link Tariff}
 * entity with MySQL DataBase
 */
public class TariffDaoImpl implements TariffDao {

    private static final Logger logger = Logger.getLogger(TariffDaoImpl.class);

    private static final String SELECT_TARIFF = "SELECT * FROM tariffs";
    private static final String INSERT_TARIFF_NAME = "INSERT INTO tariffs VALUES (DEFAULT, ?)";
    private static final String SELECT_TARIFF_BY_NAME = "SELECT * FROM tariffs WHERE name=?";
    private static final String DELETE_TARIFF_FROM_TARIFFS = "DELETE tariffs "
            + "FROM tariffs "
            + "LEFT JOIN service_tariffs "
            + "ON tariffs.id=service_tariffs.tariff_id "
            + "WHERE service_tariffs.tariff_id IS NULL";
    private static final String DELETE_TARIFF_FROM_CONTRACTS = "DELETE FROM contracts WHERE service_tariff_id=?";
    private static final String INSERT_SERVICE_TARIFFS = "INSERT INTO service_tariffs VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String DELETE_TARIFF_FROM_SERVICE_TARIFFS = "DELETE FROM service_tariffs WHERE id=?";

    /**
     * Returns all tariffs, which exist in data source
     * @return Tariff tariff
     */
    @Override
    public List<Tariff> getAll() {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_TARIFF);
            while (resultSet.next()) {
                tariffs.add(extractTariff(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_TARIFFS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_TARIFFS, ex);
        } finally {
            close(connection, statement, resultSet);
        }
        return tariffs;
    }

    /**
     * Extracts a tariff entity from the result set.
     * @param resultSet Result set from which a tariff entity will be extracted.
     * @return Tariff tariff.
     * @throws SQLException
     */
    @Override
    public Tariff extractTariff(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(resultSet.getInt(Fields.ENTITY_ID));
        tariff.setName(resultSet.getString(Fields.TARIFF_NAME));
        return tariff;
    }

    /**
     * Deletes tariff from data source
     * @param id object to be deleted
     */
    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TARIFF_FROM_CONTRACTS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement1 = connection.prepareStatement(DELETE_TARIFF_FROM_SERVICE_TARIFFS);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();

            statement = connection.createStatement();
            statement.executeUpdate(DELETE_TARIFF_FROM_TARIFFS);

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_DELETE_TARIFF, ex);
            throw new DBException(Messages.ERROR_CANNOT_DELETE_TARIFF, ex);
        } finally {
            close(preparedStatement1);
            close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Create a new tariff into data source
     * @param tariff tariff name
     */
    @Override
    public void insertTariff(String tariff) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TARIFF_NAME);
            preparedStatement.setString(1, tariff);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_INSERT_TARIFF, ex);
            throw new DBException(Messages.ERROR_CANNOT_INSERT_TARIFF, ex);
        } finally {
            close(connection, preparedStatement);
        }
    }

    /**
     * Returns tariff id with the given name.
     * @param tariff tariff name .
     * @return tariff id.
     * @throws DBException
     */
    @Override
    public int getTariffId(String tariff) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int id = 0;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_TARIFF_BY_NAME);
            preparedStatement.setString(1, tariff);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString(Fields.ENTITY_ID));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_INSERT_TARIFF, ex);
            throw new DBException(Messages.ERROR_CANNOT_INSERT_TARIFF, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return id;
    }

    /**
     * Insert tariff into 'tariffs' and 'service_tariffs' tables.
     * @param tariff        tariff name.
     * @param typeOfService service id.
     * @param price         price tariff.
     * @param description
     * @throws DBException
     */
    @Override
    public void insertTariffWithService(String tariff, int typeOfService, double price, String description) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            int id = getTariffId(tariff);
            if (id == 0) {
                insertTariff(tariff);
                id = getTariffId(tariff);
            }
            preparedStatement = connection.prepareStatement(INSERT_SERVICE_TARIFFS);
            preparedStatement.setInt(1, typeOfService);
            preparedStatement.setInt(2, id);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, description);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_INSERT_TARIFF_WITH_SERVICE, ex);
            throw new DBException(Messages.ERROR_CANNOT_INSERT_TARIFF_WITH_SERVICE, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(Tariff entity) {
    }

    @Override
    public Tariff create(Tariff entity) {
        return null;
    }

    @Override
    public Tariff getById(int id) {
        return null;
    }
}
