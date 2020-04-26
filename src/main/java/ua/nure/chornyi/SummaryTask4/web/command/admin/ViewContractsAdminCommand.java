package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.Contract;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ContractService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Viewing contracts for admin.
 */
public class ViewContractsAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(ViewContractsAdminCommand.class);

    private ContractService contractService;

    public ViewContractsAdminCommand(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("View contracts Admin command starts");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Contract> contractList = contractService.findAll();
        logger.trace("Found in DB: contracts ==> " + contractList);

        request.setAttribute("contracts", contractList);
        logger.trace("Set the request attribute: contracts ==> " + contractList);
        logger.debug("Command finished");
        return Path.PAGE_VIEW_ADMIN;
    }
}
