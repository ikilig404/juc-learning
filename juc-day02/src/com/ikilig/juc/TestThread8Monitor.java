package com.ikilig.juc;

/**
 * 1. 两个普通同步方法，两个线程，标准打印，打印结果？  one two
 * 2. 给getOne()新增Thread.sleep(3000)，打印结果？ one two
 * 3. 新增普通方法（不同步），打印结果？   three one two
 * 4. 两个普通同步方法，两个Number对象，标准打印，打印结果？ two one
 * 5. 修改getOne()为静态同步方法，打印效果？  two one
 * 6. 修改两个方法均为静态同步方法，一个Number对象，打印效果？ one two
 * 7. 一个静态同步方法，一个（非静态）同步方法，两个Number对象，打印结果？ two one
 * 8. 两个静态同步方法，两个Number对象，打印结果？ one two
 *
 * 线程八锁的关键：
 *  1. 非静态方法的锁默认为this，静态方法的锁为对应的Class实例；
 *  2. 某一时间内，只能有一个线程持有锁，无论有多少方法。
 */
public class TestThread8Monitor {

    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();

        new Thread(()->{
            number.getOne();
        }).start();

        new Thread(()->{
//            number.getTwo();
            number2.getTwo();
        }).start();

//        new Thread(()->{
//            number.getThree();
//        }).start();
    }
}

class Number {

    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("Three");
    }
}