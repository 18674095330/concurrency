package com.mmall.concurrency.example.lock;


import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 16:48 2018/5/14
 * @Description
 */

// synchronized: 不可终端锁，适合竞争不激烈，可读性好
// Lock: 可中断锁，多样化同步，竞争激烈时能维持常态
// Atomic: 竞争激烈时能维持常态，比Lock性能好，只能同步一个值
@Slf4j
@ThreadSafe
public class LockExample1 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

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
        log.info("count:{}", count);
    }

    private static synchronized void add() {
        count++;
        //HashMap
    }
}
