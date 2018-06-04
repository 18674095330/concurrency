package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 16:48 2018/5/14
 * @Description
 */

@Slf4j
@ThreadSafe
public class CountExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("excetion", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }
}

//CAS底层原理：把当前对象的值和底层的值进行对比，如果相等，才执行对应的操作，如果不想等，不停地循环操作

/** count.incrementAndGet();
 * 其中incrementAndGet() 调用unsafe.getAndAddInt(this, valueOffset, 1) + 1
 * getAndAddInt方法如下  count的副本为2，需要执行此操作
 * public final int getAndAddInt(Object var1, long var2, int var4) {  var1为count, var2 = 2, var4 = 1
 int var5;
 do {
 var5 = this.getIntVolatile(var1, var2);//var5相当于底层的值,
 } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4)); 如果var2 = var5, 则var5 = var5 + var4
 return var5;
 }
 *
 * */
