package com.sauzny.cdc.unique;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class UniqueLock {

    private int wait_count;

    private long event_id;

    private Map<Long, Boolean> cond_map = new HashMap<>();

    private String key1;

    private String key2;

    public UniqueLock() {
    }

    public UniqueLock(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getWait_count() {
        return wait_count;
    }

    public void setWait_count(int wait_count) {
        this.wait_count = wait_count;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public Map<Long, Boolean> getCond_map() {
        return cond_map;
    }

    public void setCond_map(Map<Long, Boolean> cond_map) {
        this.cond_map = cond_map;
    }


    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }
}
