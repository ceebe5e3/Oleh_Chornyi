package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.UserService;
import ua.nure.chornyi.SummaryTask4.web.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Update settings command.
 */
public class UpdateSettingsCommand extends Command {

    private static final Logger logger = Logger.getLogger(UpdateSettingsCommand.class);

    private UserService userService;

    public UpdateSettingsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Update settings command starts");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String login = request.getParameter("login");
        logger.trace("Request parameter: login ==> " + login);

        String password = request.getParameter("password");
        logger.trace("Request parameter: password ==> ");

        String language = request.getParameter("language");
        logger.trace("Request parameter: language ==> " + language);

        //if user change only language or nothing
        if (login.equals(user.getLogin()) & password.equals(user.getPassword())) {
            session.setAttribute("language", language);
            logger.trace("Set the session attribute: language ==> " + language);
            return Path.COMMAND_VIEW_SETTINGS;
        }

        if (login.equals(user.getLogin())) {
            if (StringUtils.isBlank(password) || password.length() <= 4) {
                logger.error("Invalid password");
                return Path.COMMAND_VIEW_SETTINGS;
            }
        } else {
            boolean valid = Validation.validateLoginAndPassword(login, password);

            if (!valid) {
                logger.error("Invalid data.");
                return Path.COMMAND_VIEW_SETTINGS + "&error=error";
            }
        }
        user.setLogin(login);
        user.setPassword(password);
        userService.update(user);
        logger.info("Login and password were updated.");

        session.setAttribute("user", user);
        logger.trace("Set the session attribute: user ==> " + user);

        session.setAttribute("language", language);
        logger.trace("Set the session attribute: language ==> " + language);

        logger.debug("Command finished");
        return Path.COMMAND_VIEW_SETTINGS;
    }
}
