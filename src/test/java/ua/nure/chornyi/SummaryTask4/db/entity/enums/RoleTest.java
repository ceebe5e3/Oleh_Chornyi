package ua.nure.chornyi.SummaryTask4.db.entity.enums;

import org.junit.Before;
import org.junit.Test;
import ua.nure.chornyi.SummaryTask4.db.entity.User;

import static org.junit.Assert.*;

public class RoleTest {

    private final Role role = Role.ADMIN;
    private final User user = new User();

    @Before
    public void setUpData() {
        user.setRoleId(0);
    }
    @Test
    public void testGetRole() {
        assertEquals(Role.getRole(user), Role.ADMIN);
    }
    @Test
    public void testGetName() {
        assertEquals(role.getName(), "admin");
    }

}