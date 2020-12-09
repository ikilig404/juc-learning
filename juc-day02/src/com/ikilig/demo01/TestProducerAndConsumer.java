package com.ikilig.demo01;

/**
 * 虚假唤醒问题
 */
public class TestProducerAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(producer, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(producer, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}

// 店员
//class Clerk {
//    private int product = 0;
//
//    // 进货
//    public synchronized void get() {
//        while (product >= 1) {
////        if (product >= 1) {
//            System.out.println("产品已满！");
//            try {
//                this.wait();  // 等待唤醒机制，解决产品已满还继续生产或缺货还继续进货的情况
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } //else {  // else 可能会导致线程等待，无人唤醒，虚假唤醒问题
//        System.out.println(Thread.currentThread().getName() + " : " + ++product);
//        this.notifyAll();
//        //}
//    }
//
//    // 卖货
//    public synchronized void sale() {
////        if (product <= 0) {
//        while (product <= 0) {
//            System.out.println("缺货！");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } //else {
//        System.out.println(Thread.currentThread().getName() + " : " + --product);
//        this.notifyAll();
//        // }
//    }
//}
//
//// 生产者
//class Producer implements Runnable {
//
//    private Clerk clerk;
//
//    public Producer(Clerk clerk) {
//        this.clerk = clerk;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            clerk.get();
//        }
//    }
//}
//
//// 消费者
//class Consumer implements Runnable {
//
//    private Clerk clerk;
//
//    public Consumer(Clerk clerk) {
//        this.clerk = clerk;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 20; i++) {
//            clerk.sale();
//        }
//    }
//}