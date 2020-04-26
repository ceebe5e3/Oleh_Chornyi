package ua.nure.chornyi.SummaryTask4.db.entity;

/**
 * Tariff entity.
 */
public class Tariff extends Entity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                '}';
    }
}
