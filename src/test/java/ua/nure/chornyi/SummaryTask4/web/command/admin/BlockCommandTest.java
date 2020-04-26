package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.BlockUnBlockUserDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.BlockUnBlockServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class BlockCommandTest {

    static BlockCommand blockCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        blockCommand = new BlockCommand(new BlockUnBlockServiceImpl(new BlockUnBlockUserDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        BlockCommand blockCommand1 = Mockito.mock(BlockCommand.class);
        blockCommand1.execute(request, response);
    }
}