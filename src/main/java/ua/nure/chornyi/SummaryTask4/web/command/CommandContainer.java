package ua.nure.chornyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.dao.*;
import ua.nure.chornyi.SummaryTask4.web.command.admin.*;
import ua.nure.chornyi.SummaryTask4.web.command.common.*;
import ua.nure.chornyi.SummaryTask4.web.command.subscriber.AccountPaymentCommand;
import ua.nure.chornyi.SummaryTask4.web.command.subscriber.MakeOrderCommand;
import ua.nure.chornyi.SummaryTask4.web.command.subscriber.DownloadPDFCommand;
import ua.nure.chornyi.SummaryTask4.web.command.subscriber.ViewAccountCommand;
import ua.nure.chornyi.SummaryTask4.web.service.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 */
public class CommandContainer {

    private static final Logger logger = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand(new UserServiceImpl(new UserDaoImpl()), new AccountServiceImpl(new AccountDaoImpl())));
        commands.put("logout", new LogoutCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("updateSettings", new UpdateSettingsCommand(new UserServiceImpl(new UserDaoImpl())));
        commands.put("noCommand", new NoCommand());
        commands.put("listServiceTariffs", new ListServiceTariffsCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl())));

        // client commands
        commands.put("accountPayment", new AccountPaymentCommand(new AccountServiceImpl(new AccountDaoImpl())));
        commands.put("downloadPDF", new DownloadPDFCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl())));
        commands.put("makeOrder", new MakeOrderCommand(new TariffServiceImpl(new TariffDaoImpl()), new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl()), new ContractServiceImpl(new ContractDaoImpl())));
        commands.put("viewAccount", new ViewAccountCommand(new AccountServiceImpl(new AccountDaoImpl()), new ContractServiceImpl(new ContractDaoImpl())));

        // admin commands
        commands.put("listSubscribers", new ListSubscribersCommand(new UserServiceImpl(new UserDaoImpl())));
        commands.put("addSubscriber", new AddSubscriberCommand(new UserServiceImpl(new UserDaoImpl())));
        commands.put("addSubscriberForm", new AddSubscriberFormCommand());
        commands.put("insertTariff", new InsertTariffCommand(new TariffServiceImpl(new TariffDaoImpl())));
        commands.put("insertTariffForm", new InsertTariffFormCommand(new ServiceIfImpl(new ServiceDaoImpl())));
        commands.put("deleteTariff", new DeleteTariffCommand(new TariffServiceImpl(new TariffDaoImpl())));
        commands.put("editTariff", new EditTariffCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl()), new TariffServiceImpl(new TariffDaoImpl())));
        commands.put("editTariffForm", new EditTariffFormCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl())));
        commands.put("block", new BlockCommand(new BlockUnBlockServiceImpl(new BlockUnBlockUserDaoImpl())));
        commands.put("unblock", new UnBlockCommand(new BlockUnBlockServiceImpl(new BlockUnBlockUserDaoImpl())));
        commands.put("viewAdmin", new ViewContractsAdminCommand(new ContractServiceImpl(new ContractDaoImpl())));

        logger.debug("Command container was successfully initialized");
        logger.trace("Number of commands ==> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     * @param commandName
     * Name of the command
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            logger.trace("Command not found, name ==> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
