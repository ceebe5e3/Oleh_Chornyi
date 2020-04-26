package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.ContractDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewContractsAdminCommandTest {

    static ViewContractsAdminCommand adminCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        adminCommand = new ViewContractsAdminCommand(new ContractServiceImpl(new ContractDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ViewContractsAdminCommand adminCommand1 = Mockito.mock(ViewContractsAdminCommand.class);
        adminCommand1.execute(request, response);
    }
}