package com.ikilig.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
//    private static int num = 0;
    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            while (num == 0) {
//                if (num == 0)
//                    System.out.println(num);  // println有synchronized关键字，会导致num在别的线程的更新结果可见
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
