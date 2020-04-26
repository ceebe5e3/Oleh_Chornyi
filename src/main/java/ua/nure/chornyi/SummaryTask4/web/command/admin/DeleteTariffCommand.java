package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete tariff command.
 */
public class DeleteTariffCommand extends Command {

    private TariffService tariffService;

    public DeleteTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    private static final Logger logger = Logger.getLogger(DeleteTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Delete tariff command starts");

        String[] itemId = request.getParameterValues("itemId");
        logger.trace("Request parameter: itemId ==> " + itemId);

        if (itemId != null) {
            for (String id : itemId) {
                tariffService.remove(Integer.parseInt(id));
            }
        }
        logger.info("Tariffs were deleted.");

        logger.debug("Command finished");
        return Path.COMMAND_LIST_SERVICE_TARIFFS + "&type=delete";
    }
}
