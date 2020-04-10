package com.sauzny.cdc.unique;

import com.google.common.collect.Sets;

import java.util.Set;

public class User {

    private int id;
    private String name;
    private String type;

    // 单列唯一索引 使用 demo2
    private long waitEventId;

    public long getWaitEventId() {
        return waitEventId;
    }

    public void setWaitEventId(long waitEventId) {
        this.waitEventId = waitEventId;
    }
    // 单列唯一索引 使用 demo2

    private long eventId;
    private Set<Long> waitEventIdSet = Sets.newHashSet();

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Set<Long> getWaitEventIdSet() {
        return waitEventIdSet;
    }

    public void setWaitEventIdSet(Set<Long> waitEventIdSet) {
        this.waitEventIdSet = waitEventIdSet;
    }

    public User(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "\t name='" + name + '\'' +
                "\t type='" + type + '\'' +
                "\t eventId=" + eventId +
                "\t waitEventId=" + waitEventId +
                "\t waitEventId=" + waitEventIdSet +
                '}';
    }
}
