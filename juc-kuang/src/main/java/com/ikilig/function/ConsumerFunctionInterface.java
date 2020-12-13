package com.ikilig.function;

import java.util.function.Consumer;

public class ConsumerFunctionInterface {

    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        };

        Consumer<String> consumer2 = o-> System.out.println(o);

        consumer.accept("who i am");
        consumer2.accept("you are my destiny");
    }
}
