package com.sauzny.cdc.unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo02 {

    public static void foo01(){

        // lock map
        Map<String, UniqueLock> lockMap = new HashMap<>();

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
                    UniqueLock uniqueLock = lockMap.get(user.getName());
                    synchronized(uniqueLock){
                        while(true){
                            if(user.getWaitEventId() != 0 && uniqueLock.getCond_map().get(user.getWaitEventId())){
                                System.out.println(user + " 我是 Trump，我要排队等待，说到等待，没有人比我更懂了");
                                uniqueLock.wait();
                            }else{
                                break;
                            }
                        }
                    }

                    System.out.println(name + " -- " + user);

                    synchronized(uniqueLock) {
                        uniqueLock.setWait_count(uniqueLock.getWait_count()-1);
                        uniqueLock.getCond_map().put(user.getEventId(), false);
                        uniqueLock.notifyAll();
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
                    UniqueLock uniqueLock = lockMap.get(user.getName());
                    synchronized(uniqueLock){
                        while(true){
                            if(user.getWaitEventId() != 0 && uniqueLock.getCond_map().get(user.getWaitEventId())){
                                System.out.println(user + " 我是 Trump，我要排队等待，说到等待，没有人比我更懂了");
                                uniqueLock.wait();
                            }else{
                                break;
                            }
                        }
                    }

                    System.out.println(name + " -- " + user);

                    synchronized(uniqueLock) {
                        uniqueLock.setWait_count(uniqueLock.getWait_count()-1);
                        uniqueLock.getCond_map().put(user.getEventId(), false);
                        uniqueLock.notifyAll();
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
                    UniqueLock uniqueLock = lockMap.get(user.getName());
                    synchronized(uniqueLock) {
                        while (true) {
                            if(user.getWaitEventId() != 0 && uniqueLock.getCond_map().get(user.getWaitEventId())){
                                System.out.println(user + " 我是 Trump，我要排队等待，说到等待，没有人比我更懂了");
                                uniqueLock.wait();
                            } else {
                                break;
                            }
                        }
                    }

                    System.out.println(name + " -- " + user);

                    synchronized(uniqueLock) {
                        uniqueLock.setWait_count(uniqueLock.getWait_count()-1);
                        uniqueLock.getCond_map().put(user.getEventId(), false);
                        uniqueLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        // 开始工作
        threadList.forEach(Thread::start);

        // 造数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "a1", "insert"));
        userList.add(new User(112, "Trump", "insert"));
        userList.add(new User(2, "a2", "insert"));
        userList.add(new User(113, "Trump", "insert"));
        userList.add(new User(3, "a3", "insert"));
        userList.add(new User(114, "Trump", "insert"));
        userList.add(new User(4, "a4", "insert"));
        userList.add(new User(115, "Trump", "insert"));
        userList.add(new User(5, "a5", "insert"));
        userList.add(new User(116, "Trump", "delete"));
        userList.add(new User(6, "a6", "insert"));
        userList.add(new User(117, "Trump", "insert"));
        userList.add(new User(7, "a7", "insert"));
        userList.add(new User(118, "Trump", "insert"));
        userList.add(new User(8, "a8", "insert"));
        userList.add(new User(119, "Trump", "insert"));
        userList.add(new User(9, "a9", "insert"));
        userList.add(new User(120, "Trump", "insert"));


        // 分发数据
        for(int i=0; i<userList.size(); i++){
            User user = userList.get(i);

            long eventId = i+1;
            //
            String name = user.getName();
            lockMap.putIfAbsent(name, new UniqueLock());
            UniqueLock uniqueLock = lockMap.get(name);

            uniqueLock.setWait_count(uniqueLock.getWait_count()+1);
            long wait_event_id = uniqueLock.getEvent_id();
            uniqueLock.setEvent_id(eventId);
            // true 持有 false 释放
            uniqueLock.getCond_map().put(eventId, true);

            user.setEventId(eventId);
            user.setWaitEventId(wait_event_id);
            //

            try {
                bqList.get(user.getId()%3).put(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 假设name例是唯一索引
        // 测试效果，1 线程内有序，2 name列有序
        Demo02.foo01();
    }
}
