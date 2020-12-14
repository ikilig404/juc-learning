package com.ikilig.single;

/**
 * 饿汉式单例， 浪费资源，反射会破坏单例
 */
public class Hungry {

    // 可能浪费空间
    private byte[] date1 = new byte[1024*1024];
    private byte[] date2 = new byte[1024*1024];
    private byte[] date3 = new byte[1024*1024];
    private byte[] date4 = new byte[1024*1024];

    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
