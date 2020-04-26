package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.BlockUnBlockService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Unblock command.
 */
public class UnBlockCommand extends Command {

    private BlockUnBlockService blockUnBlockService;

    public UnBlockCommand(BlockUnBlockService blockUnBlockService) {
        this.blockUnBlockService = blockUnBlockService;
    }

    private static final Logger logger = Logger.getLogger(UnBlockCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("UnBlock command starts");
        int id = Integer.parseInt(request.getParameter("unblock"));
        logger.trace("Request parameter: unblock ==> " + id);

        blockUnBlockService.unblockUser(id);
        logger.info("Subscribers were unblocked.");

        logger.debug("Command finished");
        return Path.COMMAND_LIST_SUBSCRIBERS;
    }
}
