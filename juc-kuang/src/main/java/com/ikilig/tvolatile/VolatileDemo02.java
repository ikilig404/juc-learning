package com.ikilig.tvolatile;

/**
 *  保证内存可见性
 *  不保证原子性
 *  禁止指令重排
 */
public class VolatileDemo02 {

    private volatile static int num = 0;

    public static void add() {
        num++;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {  // main  GC
            Thread.yield();
        }

        System.out.println(num);
    }
}
