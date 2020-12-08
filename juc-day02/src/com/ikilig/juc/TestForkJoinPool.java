package com.ikilig.juc;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * fork/join框架，工作窃取模式
 */
public class TestForkJoinPool {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        Instant start = Instant.now();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 10000000000L);

        Long sum = pool.invoke(task);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    @Test
    public void testTime() {
        Instant start = Instant.now();

        long sum = 0L;

        for (long i = 0; i <= 10000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    // java8 新特性
    @Test
    public void test2() {
        Instant start = Instant.now();

        Long sum = LongStream.rangeClosed(0L, 10000000000L)
                .parallel().reduce(0L, Long::sum);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L;  // 临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long mid = (start + end) / 2;

            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, mid);
            left.fork();  // 进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(mid + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
