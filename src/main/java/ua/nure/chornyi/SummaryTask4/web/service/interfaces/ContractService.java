package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Contract;

import java.sql.Date;
import java.util.List;

/**
 * Provides work with {@link Contract} entity and related to it
 */
public interface ContractService extends GenericService<Contract> {

    /**
     * Returns list of user contracts
     * @param id user id
     * @return list of user contracts
     */
    List<Contract> findUserContracts(int id);

    /**
     * @param userId user`s id.
     * @param serviceId selected service id.
     * @param tariffId selected tariff id.
     * @param date date activation.
     */
    void createNewContract(int userId, int serviceId, int tariffId, Date date);
}
