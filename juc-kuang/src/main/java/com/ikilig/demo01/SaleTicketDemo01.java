package com.ikilig.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，线程就是一个单独的资源类，没有任何附属的操作，只有域和方法
 * 多线程操作同一个资源类，把资源类放入线程即可。
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

// 资源类 OOP
class Ticket {
    // 域和方法
    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
//    public synchronized void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "完成一次售票，剩余票数：" + --number);
            }
        } finally {
            lock.unlock();
        }
    }
}
