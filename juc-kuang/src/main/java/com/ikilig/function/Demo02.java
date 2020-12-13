package com.ikilig.function;

import java.util.function.Predicate;

public class Demo02 {

    public static void main(String[] args) {
        Predicate<String> stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        };

        Predicate<String> stringPredicate2 = o -> o.isEmpty();

        System.out.println(stringPredicate.test(""));
        System.out.println(stringPredicate2.test("abc"));
    }
}
