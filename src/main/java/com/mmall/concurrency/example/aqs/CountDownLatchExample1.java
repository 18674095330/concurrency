package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 10:13 2018/5/22
 * @Description
 */

@Slf4j
public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int number = i;
            executorService.execute(() -> {
                try {
                    test(number);
                } catch (Exception e) {
                    log.info("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //在countDownLatch的计数器值降为零，才会执行后面的代码
        countDownLatch.await();
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int number) throws Exception {
        Thread.sleep(100);
        log.info("{}", number);
        Thread.sleep(100);
    }
}
