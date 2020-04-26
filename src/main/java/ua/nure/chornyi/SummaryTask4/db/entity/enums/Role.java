package ua.nure.chornyi.SummaryTask4.db.entity.enums;

import ua.nure.chornyi.SummaryTask4.db.entity.User;

/**
 * Role entity.
 */
public enum Role {
    ADMIN, SUBSCRIBER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
