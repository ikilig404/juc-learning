package com.ikilig.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {

    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者线程：").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者线程：").start();
    }
}

class Data2 {

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    /**
     * 生产 num+1
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 用if会存在虚假唤醒问题，应该用while
            while (num != 0) {
                // 等待
                condition1.await();
            }
            // 业务
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知 其他线程
            condition2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 用if会存在虚假唤醒问题，应该用while
            while (num == 0) {
                // 等待
                condition2.await();
            }
            // 业务
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知 其他线程
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
