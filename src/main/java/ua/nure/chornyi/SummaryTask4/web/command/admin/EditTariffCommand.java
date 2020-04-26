package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.TariffService;
import ua.nure.chornyi.SummaryTask4.web.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit tariff command.
 */
public class EditTariffCommand extends Command {

    private static final Logger logger = Logger.getLogger(EditTariffCommand.class);

    private ServiceTariffsService serviceTariffsService;
    private TariffService tariffService;

    public EditTariffCommand(ServiceTariffsService serviceTariffsService, TariffService tariffService) {
        this.serviceTariffsService = serviceTariffsService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Edit tariff command starts");

        String serviceTariffId = request.getParameter("serviceTariffId");
        logger.trace("Request parameter: serviceTariffId ==> " + serviceTariffId);

        String tariffName = request.getParameter("tariffName");
        logger.trace("Request parameter: tariffName ==> " + tariffName);

        String price = request.getParameter("price");
        logger.trace("Request parameter: price ==> " + price);

        String description = request.getParameter("description");
        logger.trace("Request parameter: description ==> " + description);

        ServiceTariffs serviceTariff = serviceTariffsService.getById(Integer.parseInt(serviceTariffId));

        logger.trace("Found in DB: serviceTariff ==> " + serviceTariff);

        boolean valid = Validation.insertTariffValidate(tariffName, price, description);

        if (!valid) {
            logger.error("Invalid data!");
            return Path.COMMAND_LIST_SERVICE_TARIFFS;
        }

        Tariff tariff = new Tariff();
        tariff.setId(serviceTariff.getTariff().getId());
        tariff.setName(tariffName);

        serviceTariff.setPrice(Double.parseDouble(price));
        serviceTariff.setDescription(description);

        if (!tariffName.equals(serviceTariff.getTariff().getName())) {
            serviceTariff.setTariff(tariff);

            int tariffId = tariffService.getTariffId(tariffName);

            logger.trace("Found in DB: tariffId ==> " + tariffId);

            if (tariffId == 0) {
                serviceTariffsService.editTariff(serviceTariff);
            }
        } else {
            serviceTariff.setTariff(tariff);
            serviceTariffsService.editTariff(serviceTariff);
        }

        logger.info("Tariff was edited.");
        logger.debug("Command finished");
        return Path.COMMAND_LIST_SERVICE_TARIFFS;
    }
}
