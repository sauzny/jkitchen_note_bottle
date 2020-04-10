package com.sauzny.cdc.unique;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

    private BlockingQueue<User> bq;

    public Worker(BlockingQueue<User> bq) {
        this.bq = bq;
    }

    private void doSomeBussiness(String name, User user, long start){
        System.out.println(name + " -- " + user + " -- " + start);
    }

    @Override
    public void run() {
        try {
            while (true){
                String name = Thread.currentThread().getName();

                User user = bq.take();





                // 实际应该先判断是否需要 releaseAndApply
                // 然后再 releaseAndApply
                UniqueLockManager.releaseAndApply(user, () -> doSomeBussiness(name, user, System.currentTimeMillis()));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
