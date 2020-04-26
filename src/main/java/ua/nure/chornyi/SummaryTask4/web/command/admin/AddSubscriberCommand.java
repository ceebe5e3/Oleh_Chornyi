package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.User;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Role;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.UserService;
import ua.nure.chornyi.SummaryTask4.web.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Add subscriber command.
 */
public class AddSubscriberCommand extends Command {

    private static final Logger logger = Logger.getLogger(AddSubscriberCommand.class);

    private UserService userService;

    public AddSubscriberCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Command starts");

        String fullName = request.getParameter("fullName");
        logger.trace("Request parameter: fullName ==> " + fullName);

        String dob = request.getParameter("dob");
        logger.trace("Request parameter: dob ==> " + dob);

        Date dateOfBirth = null;

        String address = request.getParameter("address");
        logger.trace("Request parameter: address ==> " + address);

        String email = request.getParameter("email");
        logger.trace("Request parameter: email ==> " + email);

        String login = request.getParameter("login");
        logger.trace("Request parameter: login ==> " + login);

        String password = request.getParameter("password");
        logger.trace("Request parameter: password ==> ****");

        String forward = Path.ERROR_PAGE;

        boolean valid = Validation.addSubscriberValidate(fullName, dob, address, email, login, password);

        if (valid) {
            try {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                dateOfBirth = new Date(date.getTime());
            } catch (ParseException e) {
                logger.error("Invalid date format");
                throw new AppException("Invalid date format");
            }

            User subscriber = new User(fullName, dateOfBirth, address, email, login, password, Role.SUBSCRIBER.ordinal());
            userService.add(subscriber);
            logger.trace("Subscriber is added." + subscriber);
            forward = Path.COMMAND_LIST_SUBSCRIBERS;

        } else {
            logger.error("Not all fields are properly filled");
            return Path.COMMAND_ADD_SUBSCRIBER_FORM + "&error=error";
        }

        logger.debug("Command finished");
        return forward;
    }
}
