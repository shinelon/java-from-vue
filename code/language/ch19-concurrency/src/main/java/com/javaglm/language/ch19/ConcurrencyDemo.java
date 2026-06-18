package com.javaglm.language.ch19;

import java.util.concurrent.*;

/**
 * 第 19 章 · 多线程入门（够用即可）。
 * 目标：会用线程池、看得懂 Spring 里的异步、亲眼看到"并发竞态"并知道 synchronized 怎么修。
 * 不碰 JUC 源码/锁原理——那是进阶，不是写后端的刚需。
 * 前端人注意：Java 没有 async/await，并发靠"线程"。
 */
public class ConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        // 1. ★ 实际开发用线程池：ExecutorService（而不是手动 new Thread）
        //    固定 4 个线程的池子，对标"最多并发 4 个任务"
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // 2. 提交带返回值的任务（Callable），得到 Future（对标 Promise！）
        Future<Integer> future = pool.submit(() -> {
            Thread.sleep(100);    // 模拟耗时操作
            return 42;
        });
        System.out.println("异步任务结果 = " + future.get());   // 阻塞等待结果（对标 await）

        pool.shutdown();   // 用完记得关闭（Spring 会帮你管，这里手动示意）

        // 3. ★ 并发竞态：8 个线程各自给计数器 +10000，期望最终 80000
        //    先看不加锁的：count++ 其实是"读-改-写"三步，多线程交错就会丢更新
        Counter unsafe = new UnsafeCounter();
        runConcurrent(8, 10000, unsafe);
        System.out.println("不加锁 counter = " + unsafe.get() + "（预期 80000，竞态导致丢失更新）");

        //    再看 synchronized 加锁的：把 ++ 变成互斥，并发也正确
        Counter safe = new SafeCounter();
        runConcurrent(8, 10000, safe);
        System.out.println("synchronized counter = " + safe.get() + "（预期 80000，加锁后正确）");
    }

    /** 用一个临时线程池跑 threads 个线程，每个给计数器 increment times 次，跑完关闭池子。 */
    private static void runConcurrent(int threads, int times, Counter counter) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            pool.execute(() -> {
                for (int j = 0; j < times; j++) {
                    counter.increment();
                }
            });
        }
        pool.shutdown();                          // 不再接受新任务
        pool.awaitTermination(5, TimeUnit.SECONDS);   // 阻塞等待这批任务全部跑完
    }
}

/** 计数器：有无加锁两种实现，共用一个接口方便对比。 */
interface Counter {
    void increment();
    int get();
}

/** 不加锁：count++ 不是原子操作，并发下会丢失更新。 */
class UnsafeCounter implements Counter {
    private int count;
    @Override
    public void increment() {
        count++;
    }
    @Override
    public int get() {
        return count;
    }
}

/** synchronized 加锁：同一时刻只有一个线程能进 increment，保证 ++ 原子。 */
class SafeCounter implements Counter {
    private int count;
    @Override
    public synchronized void increment() {
        count++;
    }
    @Override
    public int get() {
        return count;
    }
}
