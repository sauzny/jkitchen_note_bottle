package com.sauzny.cdc.unique;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo01 {

    public static void foo01(){

        // lock map
        Map<String, NameLock> lockMap = new HashMap<>();

        // 队列
        List<BlockingQueue<User>> bqList = new ArrayList<>();
        bqList.add(new LinkedBlockingQueue<>());
        bqList.add(new LinkedBlockingQueue<>());
        bqList.add(new LinkedBlockingQueue<>());

        // 工作线程
        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(() -> {
            try {
                while (true){
                    String name = Thread.currentThread().getName();

                    User user = bqList.get(0).take();
                    NameLock nameLock = lockMap.get(user.getName());
                    synchronized(nameLock){
                        if(user.getWaitEventId() != 0 && nameLock.getCond_map().get(user.getWaitEventId())){
                            nameLock.wait();
                        }

                        System.out.println(name + " -- " + user);

                        nameLock.setWait_count(nameLock.getWait_count()-1);
                        nameLock.getCond_map().put(user.getEventId(), false);
                        nameLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        threadList.add(new Thread(() -> {
            try {
                while (true){
                    String name = Thread.currentThread().getName();

                    User user = bqList.get(1).take();
                    NameLock nameLock = lockMap.get(user.getName());
                    synchronized(nameLock){
                        if(user.getWaitEventId() != 0 && nameLock.getCond_map().get(user.getWaitEventId())){
                            System.out.println(user + " 我是 250 Trump，我要最后执行，说到等待，没有人比我更懂了");
                            nameLock.wait();
                        }

                        System.out.println(name + " -- " + user);

                        nameLock.setWait_count(nameLock.getWait_count()-1);
                        nameLock.getCond_map().put(user.getEventId(), false);
                        nameLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        threadList.add(new Thread(() -> {
            try {
                while (true){
                    String name = Thread.currentThread().getName();

                    User user = bqList.get(2).take();
                    NameLock nameLock = lockMap.get(user.getName());
                    synchronized(nameLock){
                        if(user.getWaitEventId() != 0 && nameLock.getCond_map().get(user.getWaitEventId())){
                            nameLock.wait();
                        }

                        System.out.println(name + " -- " + user);

                        nameLock.setWait_count(nameLock.getWait_count()-1);
                        nameLock.getCond_map().put(user.getEventId(), false);
                        nameLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        // 造数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "a1", "insert"));
        userList.add(new User(2, "a2", "insert"));
        userList.add(new User(111, "Trump", "insert"));
        userList.add(new User(3, "a3", "insert"));
        userList.add(new User(4, "a4", "insert"));
        userList.add(new User(5, "a5", "insert"));
        userList.add(new User(111, "Trump", "delete"));
        userList.add(new User(6, "a6", "insert"));
        userList.add(new User(7, "a7", "insert"));
        userList.add(new User(250, "Trump", "insert"));
        userList.add(new User(8, "a8", "insert"));
        userList.add(new User(9, "a9", "insert"));


        // 分发数据
        for(int i=0; i<userList.size(); i++){
            User user = userList.get(i);
            //
            String name = user.getName();
            lockMap.putIfAbsent(name, new NameLock());
            NameLock nameLock = lockMap.get(name);
            nameLock.setWait_count(nameLock.getWait_count()+1);
            int wait_event_id = nameLock.getEvent_id();
            nameLock.setEvent_id(i);
            // true 持有 false 释放
            nameLock.getCond_map().put(i, true);
            user.setEventId(i);
            user.setWaitEventId(wait_event_id);
            //

            try {
                bqList.get(user.getId()%3).put(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 开始工作
        threadList.forEach(Thread::start);
    }

    public static void main(String[] args) {
        Demo01.foo01();
    }
}
