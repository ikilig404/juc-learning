package com.ikilig.demo01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++的原子性问题：i++的操作实际上分为三步：“读-改-写”
 * int i = 10;
 * i = i++;  // 结果是10
 * <p>
 * int temp = i;
 * i = i + 1;
 * i = temp;
 * <p>
 * 二、原子变量：jdk1.5以后，java.util.concurrent.atomic包下提供了常用的原子变量：
 * 1. volatile 保证内存可见性
 * 2. CAS(Compare-And-Swap)算法保证数据的原子性
 * CAS算法是硬件对于并发操作共享数据的支持
 * CAS包含三个操作数：
 * 内存值 V
 * 预估值 A
 * 更新值 B
 * 当且仅当V==A时，V = B
 */
public class TestAtomicDemo {

    public static void main(String[] args) {

        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 20; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {

//    private int serialNumber = 0;
//    private volatile int serialNumber = 0;  // 仅加volatile不能保证变量的原子性
    private AtomicInteger serialNumber = new AtomicInteger();


    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
//        return serialNumber++;
        return serialNumber.getAndIncrement();
    }
}