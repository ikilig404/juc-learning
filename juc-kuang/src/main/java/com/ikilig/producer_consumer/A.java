package com.ikilig.producer_consumer;

/**
 * 线程之间的通信问题：生产者和消费者问题
 * 线程交替执行
 */
public class A {

    public static void main(String[] args) {

        Data data = new Data();
        
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者线程：").start();
        
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者线程：").start();

    }
}

/**
 * 数字资源类
 *
 * 生产者消费者问题助记：等待 业务 通知
 */
class Data {

    private int num = 0;

    /**
     * 生产 num+1
     */
    public synchronized void increment() throws InterruptedException {
        // 用if会存在虚假唤醒问题，应该用while
        if (num != 0) {
            // 等待
            this.wait();
        }
        // 业务
        num++;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        // 通知 其他线程
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 用if会存在虚假唤醒问题，应该用while
        if (num == 0) {
            // 等待
            this.wait();
        }
        // 业务
        num--;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        // 通知 其他线程
        this.notifyAll();
    }
}
