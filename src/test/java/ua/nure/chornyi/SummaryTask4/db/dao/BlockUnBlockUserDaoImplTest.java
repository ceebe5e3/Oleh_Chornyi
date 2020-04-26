package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class BlockUnBlockUserDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void blockUser() throws SQLException {
        resultSet = statement.executeQuery("UPDATE account SET is_blocked_by_admin='BLOCKED' WHERE user_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void unblockUser() throws SQLException{
        resultSet = statement.executeQuery("UPDATE account SET is_blocked_by_admin='UNBLOCKED' WHERE user_id=?");
        assertNotNull(resultSet.next());
    }
}