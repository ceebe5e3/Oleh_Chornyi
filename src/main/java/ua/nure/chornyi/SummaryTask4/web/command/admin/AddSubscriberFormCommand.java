package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add subscriber form command.
 */
public class AddSubscriberFormCommand extends Command {

    private static final Logger logger = Logger.getLogger(AddSubscriberFormCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Add subscriber form command starts");

        String error = request.getParameter("error");
        if (error != null) {
            return Path.PAGE_ADD_SUBSCRIBER + "?error=error";
        }
        logger.debug("Command finished");

        return Path.PAGE_ADD_SUBSCRIBER;
    }
}
