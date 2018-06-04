package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 10:13 2018/5/22
 * @Description
 */

@Slf4j
public class SemaphoreExample3 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int number = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test(number);
                    semaphore.release();
                } catch (Exception e) {
                    log.info("exception", e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    //一共创建20个线程，但是通过semaphore在控制，每次只能有三个线程运行
    //每三个线程输出后，会sleep一秒，然后再释放，所有打印的效果为，每秒钟仅打印3个。

    private static void test(int number) throws Exception {
        log.info("{}", number);
        Thread.sleep(1000);
    }
}
