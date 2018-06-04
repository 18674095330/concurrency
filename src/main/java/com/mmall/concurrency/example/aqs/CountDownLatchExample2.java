package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 10:13 2018/5/22
 * @Description
 */

@Slf4j
public class CountDownLatchExample2 {

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
        //第一个参数为需要等待的时间，第二个参数为时间的单位，这里是等待10毫秒
        //等10毫秒秒直接执行下面的代码
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int number) throws Exception {
        Thread.sleep(100);
        log.info("{}", number);
    }
}
