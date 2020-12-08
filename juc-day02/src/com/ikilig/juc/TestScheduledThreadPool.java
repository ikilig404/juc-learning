package com.ikilig.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建和销毁线程的额外开销，提高了响应的速度。
 * <p>
 * 二、线程池的体系结构：
 * java.util.concurrent.Executor : 线程的使用与调度的根接口
 * |-- ExecutorService 子接口：线程池的主要接口
 * |-- ThreadPoolExecutor 实现类
 * |-- ScheduledExecutorService 子接口：线程调度
 * |-- ScheduledThreadPoolExecutor 实现类： 继承了ThreadPoolExecutor，实现了ScheduledExecutorService
 * <p>
 * 三、工具类：Executors (装饰者模式)
 * ExecutorService newFixedThreadPool(int nThreads)   创建固定大小的线程池
 * ExecutorService newCachedThreadPool()    创建缓存线程池，线程数量自动更改
 * ExecutorService newSingleThreadExecutor()  创建单个线程的线程池
 * ScheduledExecutorService newScheduledThreadPool()  创建固定大小的线程池，可以延时或定时地执行任务
 */
public class TestScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Future<Integer> schedule = pool.schedule(() -> {
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + " : " + num);
                return num;
            }, 3, TimeUnit.SECONDS);

            System.out.println(schedule.get());
        }
        pool.shutdown();


    }
}
