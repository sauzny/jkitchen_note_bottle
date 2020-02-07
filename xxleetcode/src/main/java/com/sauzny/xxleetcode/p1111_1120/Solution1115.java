package com.sauzny.xxleetcode.p1111_1120;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Solution1115 {
    public static void main(String[] args) {
        int n = 6;
        FooBar fooBar = new FooBar(n);

        IntStream.of(1, 2).parallel().forEach(i -> {
            try {
                if (i == 1) fooBar.foo(new PrintFoo());
                if (i == 2) fooBar.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

class PrintFoo implements Runnable {

    @Override
    public void run() {
        System.out.print("foo");
    }
}

class PrintBar implements Runnable {

    @Override
    public void run() {
        System.out.print("bar");
    }
}

class FooBar {

    private Semaphore s1 = new Semaphore(1);
    private Semaphore s2 = new Semaphore(1);

    private int n;

    public FooBar(int n) {
        this.n = n;
        try {
            s2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            s1.acquire();

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            s2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            s2.acquire();

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            s1.release();
        }
    }
}