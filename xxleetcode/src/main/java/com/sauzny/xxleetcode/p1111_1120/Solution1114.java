package com.sauzny.xxleetcode.p1111_1120;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Solution1114 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        int[] ints = {3, 1, 2};
        IntStream.of(ints).parallel().forEach(i -> {
            try {
                if (i == 1) foo.first(new Print(i));
                if (i == 2) foo.second(new Print(i));
                if (i == 3) foo.third(new Print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

class Print implements Runnable {

    private int i;

    public Print(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.print(i);
    }
}

class Foo {


    private CountDownLatch cdl1 = new CountDownLatch(1);

    private CountDownLatch cdl2 = new CountDownLatch(1);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        cdl1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        cdl1.await();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        cdl2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cdl1.await();
        cdl2.await();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
