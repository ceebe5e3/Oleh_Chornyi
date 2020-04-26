package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit tariff form command.
 */
public class EditTariffFormCommand extends Command {

    private static final Logger logger = Logger.getLogger(EditTariffFormCommand.class);

    private ServiceTariffsService serviceTariffsService;

    public EditTariffFormCommand(ServiceTariffsService serviceTariffsService) {
        this.serviceTariffsService = serviceTariffsService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Edit tariff form command starts");

        String itemId = request.getParameter("editItemId");
        logger.trace("Request parameter: editItemId ==> " + itemId);

        if (itemId == null) {
            return Path.COMMAND_LIST_SERVICE_TARIFFS;
        }
        int editItemId = Integer.parseInt(itemId);

        ServiceTariffs serviceTariff;
        serviceTariff = serviceTariffsService.getById(editItemId);

        logger.trace("Found in DB: serviceTariff ==> " + serviceTariff);

        String tariffName = serviceTariff.getTariff().getName();
        double price = serviceTariff.getPrice();
        String description = serviceTariff.getDescription();

        request.setAttribute("id", editItemId);
        logger.trace("Set the request attribute: id ==> " + editItemId);
        request.setAttribute("tariffName", tariffName);
        logger.trace("Set the request attribute: tariffName ==> " + tariffName);
        request.setAttribute("price", price);
        logger.trace("Set the request attribute: price ==> " + price);
        request.setAttribute("description", description);
        logger.trace("Set the request attribute: description ==> " + description);

        logger.debug("Command finished");
        return Path.PAGE_EDITING_TARIFF;
    }
}
