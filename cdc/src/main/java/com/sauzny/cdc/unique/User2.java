package com.sauzny.cdc.unique;

public class User2 extends User {
    public User2(int id, String name, String type) {
        super(id, name, type);
    }

    @Override
    public String toString() {
        return "User2{" +
                "id=" + super.getId() +
                "\t name='" + super.getName() + '\'' +
                "\t type='" + super.getType() + '\'' +
                "\t eventId=" + super.getEventId() +
                "\t waitEventId=" + super.getWaitEventIdSet() +
                '}';
    }
}
