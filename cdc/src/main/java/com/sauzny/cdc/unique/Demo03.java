package com.sauzny.cdc.unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class Demo03 {

    public static void foo01(){

        // lock map
        Map<String, UniqueLock> lockMap = new HashMap<>();

        // 创建3个队列
        List<BlockingQueue<User>> bqList = new ArrayList<>();
        IntStream.range(0,3).forEach(i -> bqList.add(new LinkedBlockingQueue<>()));

        // 每个队列分配一个工作线程，并且开始工作
        bqList.forEach(bq -> new Worker(bq).start());

        // 造数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "a1", "a1"));
        userList.add(new User(112, "Obama", "Teacher"));
        userList.add(new User(2, "a2", "a2"));
        userList.add(new User(113, "Trump", "businessman"));
        userList.add(new User(3, "a3", "a3"));
        userList.add(new User(114, "Obama", "President"));
        userList.add(new User(4, "a4", "a4"));
        userList.add(new User(115, "Obama", "President"));
        userList.add(new User(5, "a5", "a5"));
        userList.add(new User(116, "Trump", "President"));
        userList.add(new User(6, "a6", "a6"));
        userList.add(new User(117, "Trump", "Unknown"));
        userList.add(new User(7, "a7", "a7"));
        userList.add(new User(118, "Obama", "Producer"));
        userList.add(new User(8, "a8", "a8"));
        userList.add(new User(119, "Trump", "Unknown"));
        userList.add(new User(9, "a9", "a9"));
        userList.add(new User(120, "Obama", "Unknown"));

        userList.add(new User2(1, "a1", "a1"));
        userList.add(new User2(112, "Obama", "Teacher"));
        userList.add(new User2(2, "a2", "a2"));
        userList.add(new User2(113, "Trump", "businessman"));
        userList.add(new User2(3, "a3", "a3"));
        userList.add(new User2(114, "Obama", "President"));
        userList.add(new User2(4, "a4", "a4"));
        userList.add(new User2(115, "Obama", "President"));
        userList.add(new User2(5, "a5", "a5"));
        userList.add(new User2(116, "Trump", "President"));
        userList.add(new User2(6, "a6", "a6"));
        userList.add(new User2(117, "Trump", "Unknown"));
        userList.add(new User2(7, "a7", "a7"));
        userList.add(new User2(118, "Obama", "Producer"));
        userList.add(new User2(8, "a8", "a8"));
        userList.add(new User2(119, "Trump", "Unknown"));
        userList.add(new User2(9, "a9", "a9"));
        userList.add(new User2(120, "Obama", "Unknown"));


        // 分发数据
        for(int i=0; i<userList.size(); i++){
            User user = userList.get(i);

            // 实际应该先判断是否需要 hold
            // 然后再 hold
            UniqueLockManager.hold(user);

            try {
                bqList.get(user.getId()%3).put(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 假设两列唯一索引 name 和 type 两列唯一索引
        // 测试效果，1 线程内有序，2 name列有序， 3 type列有序
        Demo03.foo01();

    }
}
