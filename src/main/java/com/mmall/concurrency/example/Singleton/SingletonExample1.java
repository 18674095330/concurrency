package com.mmall.concurrency.example.Singleton;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:43 2018/5/16
 * @Description
 */

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 单线程下没有问题
 * 多线程同时得到instance 都为null，会创建多个实例
 * */

@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    public static SingletonExample1 instance = null;

    // 静态工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
