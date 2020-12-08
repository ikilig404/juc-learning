package com.ikilig.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程的方式有四种：
 *      1. 继承Thread
 *      2. 实现Runnable
 *      3. 实现Callable
 *      4. 线程池创建
 * 二、创建执行线程方式三：实现Callable接口
 *      1. 相较于实现Runnable接口的方式，带有泛型类型参数，方法可以有返回值，并且可以抛出异常；
 *      2. Callable方式的执行，需要FutureTask实现类的支持，用于接收运算结果
 *          FutureTask是Future的实现类
 *      3. FutureTask可用于闭锁操作
 */
public class TestCallable {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();

        FutureTask<Integer> result = new FutureTask<>(threadDemo);
        new Thread(result).start();

        System.out.println("------------------------");

        // 接收线程运算后的结果
        try {
            Integer sum = result.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}
