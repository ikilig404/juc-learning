package com.ikilig.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程安全问题的方式：
 * 隐式锁：
 * 1. 同步代码块 synchronized
 * 2. 同步方法 synchronized
 * 显式锁：jdk1.5以后
 * 3. 同步锁Lock：需要通过lock()方法上锁，必须通过unlock()方法释放锁
 */
public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 1; i < 4; i++) {
            new Thread(ticket, i + "号窗口").start();
        }

    }
}

class Ticket implements Runnable {

    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {

            lock.lock();  // 上锁

            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticket);
                }
            } finally {
                lock.unlock();  // 释放锁
            }
        }
    }
}
