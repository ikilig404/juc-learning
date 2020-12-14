package com.ikilig.single;

/**
 * 静态内部类实现单例模式
 */
public class StaticInnerClass {

    private StaticInnerClass() {

    }

    private static class InnerClass {
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    public static StaticInnerClass getInstance() {
        return InnerClass.INSTANCE;
    }
}
