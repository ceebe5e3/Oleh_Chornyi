package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List service tariffs command.
 */
public class ListServiceTariffsCommand extends Command {

    private static final Logger logger = Logger.getLogger(ListServiceTariffsCommand.class);

    private static Comparator<ServiceTariffs> compareByPrice = new CompareByPrice();

    private static Comparator<ServiceTariffs> compareByNameAZ = new CompareByNameAZ();

    private static Comparator<ServiceTariffs> compareByNameZA = new CompareByNameZA();

    private ServiceTariffsService serviceTariffsService;

    public ListServiceTariffsCommand(ServiceTariffsService serviceTariffsService) {
        this.serviceTariffsService = serviceTariffsService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("List service tariffs command starts");

        List<ServiceTariffs> tariffServices = serviceTariffsService.findServicesAndTariffs();

        logger.trace("Found in DB: tariffServices ==> " + tariffServices);

        request.setAttribute("tariffServices", tariffServices);
        logger.trace("Set the request attribute: tariffServices ==> " + tariffServices);

        String sort = request.getParameter("sort");
        logger.trace("Request parameter: sort ==> " + sort);

        String type = request.getParameter("type");
        logger.trace("Request parameter: type ==> " + type);

        if ("delete".equals(type)) {
            return Path.PAGE_LIST_SERVICE_TARIFFS_DELETE;
        }

        if ("edit".equals(type)) {
            return Path.PAGE_LIST_SERVICE_TARIFFS_EDIT;
        }

        if (sort != null)
            switch (sort) {
                case "price":
                    Collections.sort(tariffServices, compareByPrice);
                    break;
                case "nameAZ":
                    Collections.sort(tariffServices, compareByNameAZ);
                    break;
                case "nameZA":
                    Collections.sort(tariffServices, compareByNameZA);
                    break;
                default:
                    break;
            }

        logger.debug("Command finished");
        return Path.PAGE_LIST_TARIFFS_SERVICES;
    }

    private static class CompareByPrice implements Comparator<ServiceTariffs> {
        public int compare(ServiceTariffs st1, ServiceTariffs st2) {
            return Double.compare(st1.getPrice(), (st2.getPrice()));
        }
    }

    private static class CompareByNameAZ implements Comparator<ServiceTariffs> {
        public int compare(ServiceTariffs st1, ServiceTariffs st2) {
            return st1.getTariff().getName().compareToIgnoreCase(st2.getTariff().getName());
        }
    }

    private static class CompareByNameZA implements Comparator<ServiceTariffs>{
        public int compare(ServiceTariffs st1, ServiceTariffs st2) {
            return st2.getTariff().getName().compareToIgnoreCase(st1.getTariff().getName());
        }
    }
}
