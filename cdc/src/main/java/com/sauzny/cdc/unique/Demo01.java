package com.sauzny.cdc.unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo01 {

    public static void foo01(){


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
                    System.out.println(name + " -- " + user);

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
                    System.out.println(name + " -- " + user);
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
                    System.out.println(name + " -- " + user);
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

            try {
                bqList.get(user.getId()%3).put(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Demo01.foo01();
    }
}
