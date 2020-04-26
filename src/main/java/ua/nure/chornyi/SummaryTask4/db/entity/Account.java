package ua.nure.chornyi.SummaryTask4.db.entity;

import ua.nure.chornyi.SummaryTask4.db.entity.enums.Status;

/**
 * Account entity.
 */
public class Account extends Entity {

    private User user;

    private double money;

    private Status isBlocked;

    private Status isBlockedByAdmin;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Status getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Status isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Status getIsBlockedByAdmin() {
        return isBlockedByAdmin;
    }

    public void setIsBlockedByAdmin(Status isBlockedByAdmin) {
        this.isBlockedByAdmin = isBlockedByAdmin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", money=" + money +
                ", isBlocked=" + isBlocked +
                ", isBlockedByAdmin=" + isBlockedByAdmin +
                '}';
    }
}
