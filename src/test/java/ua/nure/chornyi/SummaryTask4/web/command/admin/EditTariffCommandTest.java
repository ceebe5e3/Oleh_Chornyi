package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.ServiceTariffsDaoImpl;
import ua.nure.chornyi.SummaryTask4.db.dao.TariffDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ServiceTariffsServiceImpl;
import ua.nure.chornyi.SummaryTask4.web.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class EditTariffCommandTest {

    static EditTariffCommand editTariffCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        editTariffCommand = new EditTariffCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl()), new TariffServiceImpl(new TariffDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        EditTariffCommand editTariffCommand1 = Mockito.mock(EditTariffCommand.class);
        editTariffCommand1.execute(request, response);
    }
}