package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ContractDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Contract;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ContractService;

import java.sql.Date;
import java.util.List;

/**
 * {@link ContractService} implementation for work with MySQL database
 */
public class ContractServiceImpl implements ContractService {

    private ContractDao contractDao;

    public ContractServiceImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    /**
     * Returns list of user contracts
     * @param id user id
     * @return list of user contracts
     */
    @Override
    public List<Contract> findUserContracts(int id) {
        return contractDao.findUserContracts(id);
    }

    /**
     * @param userId    user`s id.
     * @param serviceId selected service id.
     * @param tariffId  selected tariff id.
     * @param date      date activation.
     */
    @Override
    public void createNewContract(int userId, int serviceId, int tariffId, Date date) {
        contractDao.createNewContract(userId, serviceId, tariffId, date);
    }

    @Override
    public void add(Contract contract) {
        contractDao.create(contract);
    }

    /**
     * Returns contracts, which exist in data source
     * @return Contract contract
     */
    @Override
    public List<Contract> findAll() {
        return contractDao.getAll();
    }


    @Override
    public Contract getById(int id) {
        return contractDao.getById(id);
    }

    @Override
    public void remove(int id) {
        contractDao.delete(id);
    }

    @Override
    public Contract update(Contract contract) {
        contractDao.update(contract);
        return contract;
    }
}
