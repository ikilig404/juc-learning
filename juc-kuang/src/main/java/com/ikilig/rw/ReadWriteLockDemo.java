package com.ikilig.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        MyCacheLock myCacheLock = new MyCacheLock();

//        // 写入操作
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(() -> {
//                myCache.put(temp + "", temp + "");
//            }, String.valueOf(i + 1)).start();
//        }
//
//        // 读取操作
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(() -> {
//                myCache.get(temp + "");
//            }, String.valueOf(i + 1)).start();
//        }

        // 写入操作
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp + "", temp + "");
            }, String.valueOf(i + 1)).start();
        }

        // 读取操作
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i + 1)).start();
        }
    }
}

class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object obj) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, obj);
            System.out.println(Thread.currentThread().getName() + "写入成功");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功");
        } finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object obj) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, obj);
        System.out.println(Thread.currentThread().getName() + "写入成功");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取成功");
    }
}
