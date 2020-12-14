package com.ikilig.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        atomicInteger.compareAndSet(2020, 2021);
        System.out.println(atomicInteger.get());
    }
}
