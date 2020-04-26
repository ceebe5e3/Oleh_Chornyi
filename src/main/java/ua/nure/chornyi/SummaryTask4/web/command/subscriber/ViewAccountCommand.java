package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.db.entity.Contract;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.AccountService;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ContractService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * View account command.
 */
public class ViewAccountCommand extends Command {

    private static final Logger logger = Logger.getLogger(ViewAccountCommand.class);

    private static Comparator<Contract> compareByDate = new CompareByDate();

    private AccountService accountService;
    private ContractService contractService;

    public ViewAccountCommand(AccountService accountService, ContractService contractService) {
        this.accountService = accountService;
        this.contractService = contractService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("View account command starts");

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        Account account = accountService.getById(user.getId());
        logger.trace("Found in DB: account ==> " + account);

        List<Contract> contractList = contractService.findUserContracts(user.getId());
        logger.trace("Found in DB: contracts ==> " + contractList);

        Collections.sort(contractList, compareByDate);
        request.setAttribute("contracts", contractList);
        logger.trace("Set the request attribute: contracts ==> " + contractList);

        double money = account.getMoney();

        request.setAttribute("money", money);
        logger.trace("Set the request attribute: money ==> " + money);

        logger.debug("Command finished");
        return Path.PAGE_ACCOUNT;
    }
    private static class CompareByDate implements Comparator<Contract> {
        public int compare(Contract c1, Contract c2) {
            return c1.getDate().compareTo(c2.getDate());
        }
    }
}

