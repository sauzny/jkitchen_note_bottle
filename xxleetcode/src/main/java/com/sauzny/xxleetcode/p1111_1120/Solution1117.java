package com.sauzny.xxleetcode.p1111_1120;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution1117 {
    public static void main(String[] args) {
        String str = "HHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHH";
        List<String> list = new ArrayList<>();
        for(char c : str.toCharArray()){
            list.add(String.valueOf(c));
        }

        H2O h2o = new H2O();

        list.parallelStream().forEach(s -> {
            try {
                if ("H".equals(s)) h2o.hydrogen(new ReleaseHydrogen());
                if ("O".equals(s)) h2o.oxygen(new ReleaseOxygen());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}


class ReleaseHydrogen implements Runnable {

    @Override
    public void run() {
        System.out.print("H");
    }
}

class ReleaseOxygen implements Runnable {

    @Override
    public void run() {
        System.out.print("O");
    }
}

class H2O {

    private Semaphore s1 = new Semaphore(2);
    private Semaphore s2 = new Semaphore(1);

    private AtomicInteger ai = new AtomicInteger(0);

    public H2O() {

    }

    private synchronized void release(){
        int temp = ai.incrementAndGet();
        if(temp % 3 == 0){
            s1.release(2);
            s2.release(1);
        }
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        s1.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        s2.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        release();
    }
}
