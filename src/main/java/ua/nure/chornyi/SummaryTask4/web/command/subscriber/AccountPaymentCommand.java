package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Status;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.AccountService;
import ua.nure.chornyi.SummaryTask4.web.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Account payment command.
 */
public class AccountPaymentCommand extends Command {

    private AccountService accountService;

    public AccountPaymentCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    private static final Logger logger = Logger.getLogger(AccountPaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Account payment command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String sumOfPayment = request.getParameter("sum");
        logger.trace("Request parameter: sumOfPayment ==> " + sumOfPayment);

        String cardNumber = request.getParameter("cardNumber");
        logger.trace("Request parameter: cardNumber ==> " + cardNumber);

        String code = request.getParameter("code");
        logger.trace("Request parameter: code ==> ***");

        boolean valid = Validation.accountPaymentValidate(sumOfPayment, cardNumber, code);

        if (!valid) {
            logger.error("Not all fields are property filled");
            return Path.COMMAND_VIEW_ACCOUNT;
        }

        double sum = Double.parseDouble(sumOfPayment);

        accountService.accountPayment(user.getId(), sum);
        logger.trace("Account is paid");

        Account account = accountService.getById(user.getId());
        logger.trace("Found in DB: account ==> " + account);

        if ((account.getIsBlocked() == Status.UNBLOCKED) & (account.getIsBlockedByAdmin() == Status.UNBLOCKED)) {
            logger.trace("Set the session attribute: block ==> " + Status.UNBLOCKED);
            session.setAttribute("block", Status.UNBLOCKED);
        } else {
            logger.trace("Set the session attribute: block ==> " + Status.BLOCKED);
            session.setAttribute("block", Status.BLOCKED);
        }

        logger.debug("Command finished");
        return Path.COMMAND_VIEW_ACCOUNT;
    }
}
