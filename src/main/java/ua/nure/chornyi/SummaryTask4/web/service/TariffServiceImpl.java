package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.TariffDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.TariffService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@link TariffService} implementation for work with MySQL database
 */
public class TariffServiceImpl implements TariffService {

    private TariffDao tariffDao;

    public TariffServiceImpl(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    /**
     * Returns tariff id with the given name.
     * @param tariff tariff name
     * @return tariff id.
     */
    @Override
    public int getTariffId(String tariff) {
        return tariffDao.getTariffId(tariff);
    }

    /**
     * Insert tariff into 'tariffs' and 'service_tariffs' tables.
     * @param tariff        tariff name.
     * @param typeOfService service id.
     * @param price         price tariff.
     * @param description
     */
    @Override
    public void insertTariffWithService(String tariff, int typeOfService, double price, String description) {
        tariffDao.insertTariffWithService(tariff, typeOfService, price, description);
    }

    /**
     * Insert a new tariff into data source
     * @param tariff tariff name
     */
    @Override
    public void add(Tariff tariff) {
        tariffDao.create(tariff);
    }

    /**
     * Returns all tariffs, which exist in data source
     * @return Tariff tariff
     */
    @Override
    public List<Tariff> findAll() {
        return tariffDao.getAll();
    }

    @Override
    public Tariff getById(int id) {
        return tariffDao.getById(id);
    }

    /**
     * Deletes tariff from data source
     * @param id object to be deleted
     */
    @Override
    public void remove(int id) {
        tariffDao.delete(id);
    }

    @Override
    public Tariff update(Tariff tariff) {
        tariffDao.update(tariff);
        return tariff;
    }
}
