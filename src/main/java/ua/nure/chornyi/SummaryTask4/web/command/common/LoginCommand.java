package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.Account;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Role;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Status;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.AccountService;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.UserService;
import ua.nure.chornyi.SummaryTask4.web.utils.Encryption;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login command.
 */
public class LoginCommand extends Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private UserService userService;
    private AccountService accountService;

    public LoginCommand(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Login command starts");

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        logger.trace("Request parameter: logging ==> " + login);

        String password = Encryption.encrypt(request.getParameter("password"));
        logger.trace("Request parameter: password ==> ");

        String forward = Path.ERROR_PAGE;

        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            logger.error("AppException: Login/password cannot be empty");
            return Path.PAGE_LOGIN + "?error=error";
        }

        User user = userService.findUserByLogin(login);
        logger.trace("Found in DB: user ==> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            logger.error("errorMessage: Cannot find user with such login/password");
            return Path.PAGE_LOGIN + "?error=error";
        } else {

            Role userRole = Role.getRole(user);
            logger.trace("userRole ==> " + userRole);
            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_LIST_SUBSCRIBERS;
            }
            if (userRole == Role.SUBSCRIBER) {
                Account account = accountService.getById(user.getId());

                if ((account.getIsBlocked() == Status.UNBLOCKED) & (account.getIsBlockedByAdmin() == Status.UNBLOCKED)) {
                    logger.trace("Set the session attribute: block ==> " + Status.UNBLOCKED);
                    session.setAttribute("block", Status.UNBLOCKED);
                } else {
                    logger.trace("Set the session attribute: block ==> " + Status.BLOCKED);
                    session.setAttribute("block", Status.BLOCKED);
                }

                forward = Path.COMMAND_LIST_SERVICE_TARIFFS;
            }

            Cookie loginCookie = new Cookie("login", login);
            loginCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(loginCookie);

            session.setAttribute("user", user);
            logger.trace("Set the session attribute: user ==> " + user);

            session.setAttribute("userRole", userRole);
            logger.trace("Set the session attribute: userRole ==> " + userRole);

            logger.info("User " + user + " logged as " + userRole.toString().toLowerCase());
        }
        logger.debug("Command finished");
        return forward;
    }
}
