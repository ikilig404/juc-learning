package com.ikilig.lock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        }, "T2").start();
    }
}
