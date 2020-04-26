package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.TariffService;
import ua.nure.chornyi.SummaryTask4.web.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Insert tariff command.
 */
public class InsertTariffCommand extends Command {

    private static final Logger logger = Logger.getLogger(InsertTariffCommand.class);

    private TariffService tariffService;

    public InsertTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Insert tariff command starts");

        String name = request.getParameter("name");
        logger.trace("Request parameter: name ==> " + name);

        String typeOfService = request.getParameter("typeOfService");
        logger.trace("Request parameter: typeOfService ==> " + typeOfService);

        String price = request.getParameter("price");
        logger.trace("Request parameter: price ==> " + price);

        String description = request.getParameter("description");
        logger.trace("Request parameter: description ==> " + description);

        boolean valid = Validation.insertTariffValidate(name, price, description);

        if (!valid) {
            logger.error("Invalid data");
            return Path.COMMAND_INSERT_TARIFF_FORM + "&error=error";
        }

        int serviceType = Integer.parseInt(typeOfService);
        double priceParse = Double.parseDouble(price);

        tariffService.insertTariffWithService(name, serviceType, priceParse, description);
        logger.info("Tariff was added.");

        logger.debug("Command finished");

        return Path.COMMAND_LIST_SERVICE_TARIFFS;
    }
}
