package com.sauzny.cdc.unique;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

public class UniqueLockManager {

    // tableName -> (tableName && uniqueColumn && Value -> lock)
    //private Map<String, Map<String, UniqueLock>> uniqueLockTable = new HashMap<>();
    private static Table<String, String, UniqueLock> uniqueLockTable = HashBasedTable.create();

    private static AtomicLong atomicLong = new AtomicLong(0);

    public static void hold(User user) {
        // 全局事件id
        long eventId = atomicLong.incrementAndGet();

        // 模拟 获取 key1
        final String key1 = key1(user);

        Lists.newArrayList("name", "type").forEach(column -> {

            // 模拟 获取 key2
            String key2 = key2(user, key1, column);

            // 获取 lock 对象
            UniqueLock uniqueLock = uniqueLockTable.get(key1, key2);
            if (uniqueLock == null) {
                uniqueLock = new UniqueLock(key1, key2);
                uniqueLockTable.put(key1, key2, uniqueLock);
            }

            synchronized (uniqueLock) {
                // 制作 lock
                uniqueLock.setWait_count(uniqueLock.getWait_count() + 1);
                long wait_event_id = uniqueLock.getEvent_id();
                uniqueLock.setEvent_id(eventId);
                // true 持有 false 释放
                uniqueLock.getCond_map().put(eventId, true);

                //
                user.setEventId(eventId);
                user.getWaitEventIdSet().add(wait_event_id);
            }
        });

    }

    public static void releaseAndApply(User user, UniqueLockDealBusiness uniqueLockDealBusiness) {

        List<UniqueLock> uniqueLockList = Lists.newArrayList();

        // 模拟 获取 key1
        final String key1 = key1(user);

        try {
            // lambda表达式无法抛出受检异常
            for (String column : Lists.newArrayList("name", "type")) {

                // 模拟 获取 key2
                String key2 = key2(user, key1, column);

                UniqueLock uniqueLock = uniqueLockTable.get(key1, key2);

                // 获取锁

                synchronized (uniqueLock) {
                    boolean isAllRelease = false;
                    while (!isAllRelease) {
                        for (long waitEventId : user.getWaitEventIdSet()) {
                            if (waitEventId != 0 && uniqueLock.getCond_map().getOrDefault(waitEventId, false)) {
                                System.out.println(user + "  " + key1 + "  " + key2 + "  我要排队等待  " + waitEventId);
                                uniqueLock.wait();
                                isAllRelease = false;
                                break;
                            } else {
                                isAllRelease = true;
                            }
                        }
                    }
                }


                // 获取锁 自旋方式
                /*
                boolean isAllRelease = false;
                while (!isAllRelease) {
                    for (int waitEventId : user.getWaitEventIdSet()) {
                        if (waitEventId != 0 && uniqueLock.getCond_map().getOrDefault(waitEventId, false)) {
                            System.out.println(user + "  " + key1 + "  " + key2 + "  我要排队等待  " + waitEventId);
                            isAllRelease = false;
                            break;
                        } else {
                            isAllRelease = true;
                        }
                    }
                }
                */
                uniqueLockList.add(uniqueLock);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(user + "获取锁时，发生异常" + e.getMessage());
        }

        // do the business
        uniqueLockDealBusiness.apply();


        // 释放锁
        uniqueLockList.forEach(uniqueLock -> {
            synchronized (uniqueLock) {
                uniqueLock.setWait_count(uniqueLock.getWait_count() - 1);
                if (uniqueLock.getWait_count() == 0) {
                    //uniqueLock.setEvent_id(0);
                    //uniqueLock.getCond_map().clear();
                    uniqueLockTable.remove(uniqueLock.getKey1(), uniqueLock.getKey2());
                    System.out.println("销毁锁" + user);
                } else {
                    uniqueLock.getCond_map().put(user.getEventId(), false);
                    user.getWaitEventIdSet().forEach(uniqueLock.getCond_map()::remove);
                    // 如果是自旋方式获取锁，则不需要notifyAll
                    uniqueLock.notifyAll();
                }
            }
        });
    }

    private static String key1(User user) {
        String key1 = "user";
        if (user instanceof User2) {
            key1 = "user2";
        }
        return key1;
    }

    private static String key2(User user, String key1, String column) {
        String value = user.getName();
        if ("type".equals(column)) {
            value = user.getType();
        }
        return new StringJoiner("_").add(key1).add(column).add(value).toString();
    }

    @FunctionalInterface
    interface UniqueLockDealBusiness {
        void apply();
    }
}
