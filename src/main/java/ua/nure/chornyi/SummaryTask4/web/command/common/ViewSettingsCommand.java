package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * View settings command.
 */
public class ViewSettingsCommand extends Command {

    private static final Logger logger = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        logger.debug("View settings command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("login", user.getLogin());
        request.setAttribute("password", user.getPassword());

        Object locales = request.getServletContext().getAttribute("locales");
        logger.trace("Found in Servlet Context: locales ==> " + locales);

        request.setAttribute("locales", locales);
        logger.trace("Set the request attribute: locales ==> " + locales);

        logger.debug("Command finished");

        String error = request.getParameter("error");
        if (error != null) {
            return Path.PAGE_SETTINGS + "?error=error";
        }
        return Path.PAGE_SETTINGS;
    }
}
