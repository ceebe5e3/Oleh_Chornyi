package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Role;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * List subscribers command.
 */
public class ListSubscribersCommand extends Command {

    private static final Logger logger = Logger.getLogger(ListSubscribersCommand.class);

    private UserService userService;

    public ListSubscribersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("List subscribers command starts");

        // get subscribers list
        List<User> subscribers = userService.findSubscribers(Role.SUBSCRIBER.ordinal());
        logger.trace("Found in DB: subscribers ==> " + subscribers);

        // put subscribers list to the request
        request.setAttribute("subscribers", subscribers);
        logger.trace("Set the request attribute: subscribers ==> " + subscribers);

        logger.debug("Command finished");
        return Path.PAGE_LIST_SUBSCRIBERS;
    }
}
