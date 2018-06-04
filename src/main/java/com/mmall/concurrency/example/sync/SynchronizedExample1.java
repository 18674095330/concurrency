package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:27 2018/5/15
 * @Description
 */
@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块: 大括号括起来的代码，作用于调用的对象
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    // 修饰一个方法：整个方法，作用于调用对象
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}", i);
        }
    }

    // 修饰静态方法：整个静态方法，作用于所有对象
    public static synchronized void test3(int k) {
        for (int i = 0; i < 10; i++) {
            log.info("test3 - {} - {}", k, i);
        }
    }

    // 修饰类：括号括起来的部分，作用于所有对象
    public void test4(int k) {
        synchronized (SynchronizedExample1.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test4 - {} - {}", k, i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();

        /*executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example1.test1();
        });*/

        /*executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });*/

        /*executorService.execute(() -> {
            example1.test2();
        });
        executorService.execute(() -> {
            example1.test2();
        });*/

        /*executorService.execute(() -> {
            example1.test2();
        });
        executorService.execute(() -> {
            example2.test2();
        });*/

        /*executorService.execute(() -> {
            example1.test3(1);
        });
        executorService.execute(() -> {
            example2.test3(2);
        });*/

        executorService.execute(() -> {
            example1.test4(1);
        });
        executorService.execute(() -> {
            example2.test4(2);
        });
    }


}
