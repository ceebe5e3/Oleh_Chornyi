package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.ServiceTariffsDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ServiceTariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServiceTariffsCommandTest {

    static ListServiceTariffsCommand listSubscribersCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        listSubscribersCommand = new ListServiceTariffsCommand(new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ListServiceTariffsCommand listServiceTariffsCommand1 = Mockito.mock(ListServiceTariffsCommand.class);
        listServiceTariffsCommand1.execute(request, response);
    }
}