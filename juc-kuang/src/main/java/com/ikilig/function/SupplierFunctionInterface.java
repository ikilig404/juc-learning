package com.ikilig.function;

import java.util.function.Supplier;

public class SupplierFunctionInterface {

    public static void main(String[] args) {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("get()");
                return 1024;
            }
        };

        Supplier<Integer> supplier2 = () -> {
            System.out.println("get() 2");
            return 1023;
        };

        System.out.println(supplier.get());

        System.out.println(supplier2.get());
    }
}
