package com.ikilig.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例,  反射还是会破坏单例
 */
public class LazyMan {

    private static boolean flag = false;  // 个人理解，标志位没办法解决到底是getInstance开始还是反射开始

    private LazyMan() throws Exception {
        // 这个办法的前提是先用正常getInstance创建了单例，若一开始就用反射，还是无法阻止
        synchronized (LazyMan.class) {
            if (flag == false) {
                flag = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏单例");
            }
            System.out.println(Thread.currentThread().getName() + "单例的构造函数只调用一次");
        }
    }

//    private static LazyMan LAZYMAN;
    private volatile static LazyMan LAZYMAN;

    // 不加锁在多线程下不能保证单例
//    public static LazyMan getInstance() {
//        if (LAZYMAN == null) {
//            LAZYMAN = new LazyMan();
//        }
//        return LAZYMAN;
//    }

//    /**
//     * 多线程下可以保证单例，但每个线程都要进行同步操作，效率低
//     * @return
//     */
//    public synchronized static LazyMan getInstance() {
//        if (LAZYMAN == null) {
//            LAZYMAN = new LazyMan();
//        }
//        return LAZYMAN;
//    }

    /**
     * 双重检测锁，DCL懒汉式
     * @return
     */
    public static LazyMan getInstance() throws Exception {
        // 单例还没实例化时，需要抢锁，所以同步后面的代码，实例之后，这个条件可以过滤掉同步这一操作
        // 这个判断语句可以并发，所以，后面的指令重排可能导致其他线程认为LAZYMAN不空，getInstance()直接返回一个指向未初始化内存空间的对象
        if (LAZYMAN == null) {
            synchronized (LazyMan.class) {
                if (LAZYMAN == null) {
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化内存空间
                     * 3. 把变量指向内存空间，即变量不为空了
                     *
                     * 由于指令重排，可能导致132的顺序，变量将指向一个为被初始化的内存空间
                     */
                    LAZYMAN = new LazyMan();  // 不是原子性操作
                }
            }
        }
        return LAZYMAN;
    }


    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                LazyMan.getInstance();
//            }).start();
//        }

//        LazyMan instance1 = LazyMan.getInstance();
//        LazyMan instance2 = LazyMan.getInstance();
//        System.out.println(instance1 == instance2);

        // 反射破坏单例
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance3 = declaredConstructor.newInstance();
        System.out.println(instance3);
        LazyMan instance4 = declaredConstructor.newInstance();
        System.out.println(instance4);
//        System.out.println(instance1 == instance3);
        System.out.println(instance3 == instance4);
    }
}
