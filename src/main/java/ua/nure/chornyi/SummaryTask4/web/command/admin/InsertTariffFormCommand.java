package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.Service;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceIf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Insert tariff form command.
 */
public class InsertTariffFormCommand extends Command {

    private static final Logger logger = Logger.getLogger(InsertTariffFormCommand.class);

    private ServiceIf serviceIf;

    public InsertTariffFormCommand(ServiceIf serviceIf) {
        this.serviceIf = serviceIf;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Insert tariff form command starts");
        List<Service> serviceList = new ArrayList<Service>();

        serviceList = serviceIf.findAll();

        logger.trace("Found in DB: services ==> " + serviceList);

        request.setAttribute("services", serviceList);

        logger.trace("Set the request attribute: services ==> " + serviceList);

        String error = request.getParameter("error");

        if (error != null) {
            return Path.PAGE_INSERT_TARIFF + "?error=error";
        }

        logger.debug("Command finished");
        return Path.PAGE_INSERT_TARIFF;
    }
}
