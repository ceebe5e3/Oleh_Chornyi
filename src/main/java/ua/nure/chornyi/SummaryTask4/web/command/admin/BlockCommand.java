package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.BlockUnBlockService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Block command.
 */
public class BlockCommand extends Command {

    private BlockUnBlockService blockUnBlockService;

    public BlockCommand(BlockUnBlockService blockUnBlockService) {
        this.blockUnBlockService = blockUnBlockService;
    }

    private static final Logger logger = Logger.getLogger(BlockCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Block command starts");
        int id = Integer.parseInt(request.getParameter("block"));
        logger.trace("Request parameter: id to block ==> " + id);

        blockUnBlockService.blockUser(id);
        logger.info("Subscribers were blocked.");

        logger.debug("Command finished");
        return Path.COMMAND_LIST_SUBSCRIBERS;
    }
}
