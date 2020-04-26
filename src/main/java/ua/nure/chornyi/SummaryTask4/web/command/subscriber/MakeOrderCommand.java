package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ContractService;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Make order command.
 */
public class MakeOrderCommand extends Command {

    private static final Logger logger = Logger.getLogger(MakeOrderCommand.class);

    private int selectedTariffId = 0;

    private TariffService tariffService;
    private ServiceTariffsService serviceTariffsService;
    private ContractService contractService;

    public MakeOrderCommand(TariffService tariffService, ServiceTariffsService serviceTariffsService, ContractService contractService) {
        this.tariffService = tariffService;
        this.serviceTariffsService = serviceTariffsService;
        this.contractService = contractService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Create order command starts");

        List<Tariff> tariffs = tariffService.findAll();
        logger.trace("Found in DB: tariffs ==> " + tariffs);

        request.setAttribute("tariffs", tariffs);
        logger.trace("Set the session attribute: tariffs ==> " + tariffs);

        String tariffID = request.getParameter("tariffID");
        logger.trace("Request parameter: tariffID ==> " + tariffID);

        String[] selectedServices = request.getParameterValues("serviceID");
        logger.trace("Request parameter: selectedServices ==> " + selectedServices);

        String forward = Path.ERROR_PAGE;

        //if the form is empty
        if (tariffID == null & selectedServices == null) {
            return Path.PAGE_MAKE_ORDER;
        }

        //when subscriber selects tariff
        if (tariffID != null & selectedServices == null) {
            selectedTariffId = Integer.parseInt(tariffID);
            List<ServiceTariffs> serviceTariffs = serviceTariffsService.findServiceTariffsByTariffID(selectedTariffId);
            logger.trace("Found in DB: serviceTariffs ==> " + serviceTariffs);
            request.setAttribute("serviceTariffs", serviceTariffs);
            logger.trace("Set the request attribute: serviceTariffs ==> " + serviceTariffs);
            forward = Path.PAGE_MAKE_ORDER;
        }

        //when subscriber selects services
        if (selectedServices != null) {
            java.sql.Date date = new java.sql.Date(new Date().getTime());
            User user = (User) request.getSession().getAttribute("user");
            for (String service : selectedServices) {
                contractService.createNewContract(user.getId(), Integer.parseInt(service), selectedTariffId, date);
            }
            logger.info("New contracts were made.");
            forward = Path.COMMAND_VIEW_ACCOUNT;
        }

        logger.debug("Command finished");
        return forward;
    }
}
