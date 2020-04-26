package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Account;

/**
 * Provides work with {@link Account} entity and related to it
 */
public interface AccountService extends GenericService<Account> {

    /**
     * Returns amount of payments by user id and it's destination
     * @param userId user id who payments you want
     * @param sum of entries
     */
    void accountPayment(int userId, double sum);
}
