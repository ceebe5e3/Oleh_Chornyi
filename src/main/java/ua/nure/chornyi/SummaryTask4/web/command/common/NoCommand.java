package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 */
public class NoCommand extends Command {

    private static final Logger logger = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("No command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        logger.error("Set the request attribute: errorMessage ==> " + errorMessage);

        logger.debug("Command finished");
        return Path.ERROR_PAGE;
    }
}
