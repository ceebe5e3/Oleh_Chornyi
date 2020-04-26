package ua.nure.chornyi.SummaryTask4.db.entity;

/**
 * Basic common parent for all entities.
 */
public abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
