package com.sauzny.xxleetcode.p1111_1120;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Solution1116 {
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        IntStream.of(1, 2, 3).parallel().forEach(i -> {
            if (i == 1) new ZeroPrint(zeroEvenOdd).run();
            if (i == 2) new EvenPrint(zeroEvenOdd).run();
            if (i == 3) new OddPrint(zeroEvenOdd).run();
        });
    }
}

class ZeroPrint implements Runnable {

    private ZeroEvenOdd zeroEvenOdd;

    public ZeroPrint(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            this.zeroEvenOdd.zero(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class EvenPrint implements Runnable {

    private ZeroEvenOdd zeroEvenOdd;

    public EvenPrint(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            this.zeroEvenOdd.even(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class OddPrint implements Runnable {

    private ZeroEvenOdd zeroEvenOdd;

    public OddPrint(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            this.zeroEvenOdd.odd(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ZeroEvenOdd {

    private Semaphore s1 = new Semaphore(1);
    private Semaphore s2 = new Semaphore(1);
    private Semaphore s3 = new Semaphore(1);

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;

        try {
            s2.acquire();
            s3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i <=n ;i++ ){
            s1.acquire();
            printNumber.accept(0);
            s3.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i <=n ;i++ ){
            s2.acquire();
            if(i%2 == 0) printNumber.accept(i);
            s1.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i <=n ;i++ ){
            s3.acquire();
            if(i%2 == 1) printNumber.accept(i);
            s2.release();
        }
    }
}
