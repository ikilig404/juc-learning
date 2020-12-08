package com.ikilig.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替的例子
 * 编写一个程序，开启三个线程，这三个线程的ID分别为A、B、C，每个线程将自己的ID在屏幕上打印10遍，要求输出的结果必须按顺序显示，
 * 如：ABCABCABC... 依次递归
 */
public class TestABCAlternatelyPrint {

    public static void main(String[] args) {
        AlternatelyPrintDemo alternatelyPrintDemo = new AlternatelyPrintDemo();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                alternatelyPrintDemo.loopA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                alternatelyPrintDemo.loopB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                alternatelyPrintDemo.loopC();
            }
        },"C").start();
    }
}

class AlternatelyPrintDemo {

    private int number = 1; // 当前正在执行线程的标记

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA() {
        lock.lock();
        try {
            // 判断
            if (number != 1) {
                condition1.await();
            }
            // 打印
//            for (int i = 0; i < 10; i++) {
                System.out.print(Thread.currentThread().getName());
//            }
            // 唤醒
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();
        try {
            // 判断
            if (number != 2) {
                condition2.await();
            }
            // 打印
//            for (int i = 0; i < 10; i++) {
                System.out.print(Thread.currentThread().getName());
//            }
            // 唤醒
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        lock.lock();
        try {
            // 判断
            if (number != 3) {
                condition3.await();
            }
            // 打印
//            for (int i = 0; i < 10; i++) {
                System.out.print(Thread.currentThread().getName());
//            }
            // 唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
