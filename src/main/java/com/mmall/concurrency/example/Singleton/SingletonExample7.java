package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:34 2018/5/17
 * @Description
 */

/**
 * 使用枚举保证线程的安全性
 * 比懒汉安全，与饿汉相比，不会造成资源浪费
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
