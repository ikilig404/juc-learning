package com.ikilig.demo01;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList   CopyOnWriteArraySet   在写入时复制
 * 注意：添加操作多时，效率低，因为每次添加时都会复制，开销大；并发迭代时，可以用于提高效率。
 * 参考博客：<url>https://blog.csdn.net/for_my_life/article/details/89387589<url/>
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {

        HelloThread helloThread = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(helloThread).start();
        }
    }
}

class HelloThread implements Runnable {

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());  // 虽然集合是线程安全的，但也会引起并发修改异常
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("AA");
        }
    }
}