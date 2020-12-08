package com.ikilig.juc;

import java.util.ArrayList;
import java.util.List;
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
public class TestThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ThreadPoolDemo tpd = new ThreadPoolDemo();


        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Callable接口做线程池提交方法的参数
            Future<Integer> future = pool.submit(() -> {
                int sum = 0;

                for (int j = 0; j <= 100; j++) {
                    sum += j;
                }

                return sum;
            });

            list.add(future);
        }

        pool.shutdown();

        for (Future<Integer> item : list) {
            System.out.println(item.get());
        }

//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        // 2. 为线程池中的线程分配任务
//        for (int i = 0; i < 10; i++) {
//            pool.submit(tpd);
//        }
//
//        // 3. 关闭线程池
//        pool.shutdown();
    }
}

class ThreadPoolDemo implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        while (i <= 1000) {
            System.out.println(Thread.currentThread().getName() + " : " + i++);
        }
    }
}
