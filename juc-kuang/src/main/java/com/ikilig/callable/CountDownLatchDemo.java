package com.ikilig.callable;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "go out");
                latch.countDown();  //计数器减一
            }, String.valueOf(i+1)).start();
        }
        latch.await();  // 等待计数器归零，然后再向下执行

        System.out.println("Close Door");
    }
}
