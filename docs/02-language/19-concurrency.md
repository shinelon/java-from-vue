# 多线程入门（够用即可）

JS 是**单线程**的（靠事件循环 + async/await 模拟并发）。Java 是**真·多线程**——多个线程真的同时跑。本章只讲"写后端够用"的程度。

## 线程 = 一个独立的执行流

```java
Thread t = new Thread(() -> System.out.println("我在新线程里跑"));
t.start();
```

但实际开发**很少直接 new Thread**（开销大、难管理），而是用**线程池**。

## ★ 线程池 ExecutorService（重点）

```java
ExecutorService pool = Executors.newFixedThreadPool(2);   // 2 个线程的池子

// 提交有返回值的任务 → 得到 Future（强烈对标 Promise！）
Future<Integer> future = pool.submit(() -> {
    Thread.sleep(100);
    return 42;
});
Integer result = future.get();   // 阻塞等结果（对标 await）

pool.shutdown();   // 用完关闭
```

!!! tip "Future ≈ Promise"
    - `pool.submit(task)` 返回 `Future` —— 对标 `new Promise`。
    - `future.get()` 阻塞等待 —— 对标 `await`。
    - Java 8 还有 `CompletableFuture`，能 `.thenApply()` 链式调用，更接近 Promise 的用法。

## 为什么用线程池

- 直接 `new Thread` 每次都创建/销毁，开销大。
- 线程池**复用**固定数量的线程，任务排队执行。
- Spring Boot 内置 Tomcat 就是个线程池：每个 HTTP 请求分配一个线程处理。

## synchronized 防并发

```java
class Counter {
    private int count;
    public synchronized void increment() { count++; }   // 加锁，保证原子
}
```

多个线程同时调 `increment()`，没有 `synchronized` 的话 `count++` 可能丢数据（它不是原子操作）。加 `synchronized` = 同一时刻只有一个线程能进入这个方法。

## 够用清单

| 你需要 | 用什么 |
|---|---|
| 跑异步任务拿结果 | `ExecutorService.submit()` + `Future` |
| 让任务延迟/定时执行 | `ScheduledExecutorService` |
| 保护共享数据 | `synchronized` |
| 看懂 Spring `@Async` | 它底层就是提交到线程池 |

!!! info "虚拟线程在哪？"
    JDK 21 的虚拟线程让并发代码更简单，但本书基线是 JDK 8。虚拟线程放《第五篇》提一句。

## 完整可运行示例

```java
--8<-- "language/ch19-concurrency/src/main/java/com/javaglm/language/ch19/ConcurrencyDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：注解与反射](18-annotation-reflection.md) ｜ [第三篇 · Spring Boot 后端](../03-spring/20-ioc-di.md)
