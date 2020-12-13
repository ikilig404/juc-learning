package com.ikilig.pool;

import java.util.concurrent.*;

/**
 * Executors  工具类
 */
public class Demo01 {

    public static void main(String[] args) {
//        ExecutorService pool1 = Executors.newSingleThreadExecutor();
//        ExecutorService pool2 = Executors.newFixedThreadPool(5);
//        ExecutorService pool3 = Executors.newCachedThreadPool();
        // 自定义线程池，阿里巴巴开发规范
        ExecutorService pool1 = new ThreadPoolExecutor(2, 5,
                3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        try {
            pool1.execute(()-> System.out.println(Thread.currentThread().getName() + "ok"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool1.shutdown();
        }
    }
}
