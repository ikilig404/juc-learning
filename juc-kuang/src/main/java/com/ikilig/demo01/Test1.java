package com.ikilig.demo01;

/**
 * 一、 wait() 和 sleep() 的区别
 *      1. 这两个方法来自不同的类分别是Thread和Object
 *      2. 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法(锁代码块和方法锁)。
 *      3. wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
 *      4. sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
 */
public class Test1 {

    public static void main(String[] args) {
//        new Thread().start();
        // 获取CPU的核数
        // CPU密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 线程的六种状态
        Thread.State state = new Thread().getState();
        System.out.println(state);


    }
}
