package com.ikilig.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调
 */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
            int i = 10 / 0;
            return 1024;
        });

//        TimeUnit.SECONDS.sleep(5);
//        System.out.println("11111");
//        voidCompletableFuture.get();

        Integer result = integerCompletableFuture.whenComplete((t, u) -> {  // 不论是否异常都会回调
            System.out.println("t=>" + t);
            System.out.println("u=>" + u);
        }).exceptionally(e -> {   // 发生异常的回调函数
            System.out.println(e.getMessage());
            return 1234;
        }).get();

        System.out.println(result);
    }
}
