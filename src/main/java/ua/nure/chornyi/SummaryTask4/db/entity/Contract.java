package ua.nure.chornyi.SummaryTask4.db.entity;

import java.util.Date;

/**
 * Contract entity.
 */
public class Contract extends Entity {

    private User user;

    private ServiceTariffs serviceTariffs;

    private Date date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceTariffs getServiceTariffs() {
        return serviceTariffs;
    }

    public void setServiceTariffs(ServiceTariffs serviceTariffs) {
        this.serviceTariffs = serviceTariffs;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "user=" + user +
                ", serviceTariffs=" + serviceTariffs +
                ", date=" + date +
                '}';
    }
}
