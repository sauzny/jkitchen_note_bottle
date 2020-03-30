package com.sauzny.cdc.unique;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class UniqueLock {

    private int wait_count;

    private int event_id;

    private Map<Integer, Boolean> cond_map = new HashMap<>();

    public int getWait_count() {
        return wait_count;
    }

    public void setWait_count(int wait_count) {
        this.wait_count = wait_count;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Map<Integer, Boolean> getCond_map() {
        return cond_map;
    }

    public void setCond_map(Map<Integer, Boolean> cond_map) {
        this.cond_map = cond_map;
    }

}
