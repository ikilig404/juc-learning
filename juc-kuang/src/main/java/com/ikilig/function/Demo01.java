package com.ikilig.function;

import java.util.function.Function;

public class Demo01 {

    public static void main(String[] args) {
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };
        Function function1= s -> s;

        System.out.println(function1.apply(1 + 1));
    }
}
