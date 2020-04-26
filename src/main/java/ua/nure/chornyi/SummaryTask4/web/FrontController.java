package ua.nure.chornyi.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front Controller
 */
public class FrontController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Method get()");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Method post()");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.debug("Front Controller starts");

        String commandName = req.getParameter("command");
        logger.trace("Request parameter: command ==> " + commandName);
        Command command = CommandContainer.get(commandName);
        logger.trace("Obtained command ==> " + command);

        String forward = Path.ERROR_PAGE;
        try {
            forward = command.execute(req, resp);

        } catch (AppException ex) {
            System.out.println(ex.getMessage());
            System.out.println("DBException#main" + ex.getMessage());
            req.setAttribute("errorMessage", ex.getMessage());
            logger.error(ex.getMessage());
        }
        logger.trace("Forward address ==> " + forward);
        logger.debug("Controller finished, now go to forward address ==> " + forward);

        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
