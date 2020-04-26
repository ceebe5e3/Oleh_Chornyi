package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.TariffDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class DeleteTariffCommandTest {

    static DeleteTariffCommand deleteTariffCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        deleteTariffCommand = new DeleteTariffCommand(new TariffServiceImpl(new TariffDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        DeleteTariffCommand deleteTariffCommand1 = Mockito.mock(DeleteTariffCommand.class);
        deleteTariffCommand1.execute(request, response);
    }
}