package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.AccountDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.AccountService;

import java.util.List;

/**
 * {@link AccountService} implementation for work with MySQL database
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Returns amount of payments by user id and it's destination
     * @param userId user id who payments you want
     * @param sum    of entries
     */
    @Override
    public void accountPayment(int userId, double sum) {
        accountDao.accountPayment(userId, sum);
    }


    @Override
    public void add(Account account) {
        accountDao.create(account);
    }


    @Override
    public List<Account> findAll() {
        return accountDao.getAll();
    }

    /**
     * Returns accounts, which exist in data source
     * @param id parameter to specify selection
     * @return Account account
     */
    @Override
    public Account getById(int id) {
        return accountDao.getById(id);
    }


    @Override
    public void remove(int id) {
        accountDao.delete(id);
    }


    @Override
    public Account update(Account account) {
        accountDao.update(account);
        return account;
    }
}
