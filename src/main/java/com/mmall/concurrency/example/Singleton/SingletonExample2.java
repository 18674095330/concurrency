package com.mmall.concurrency.example.Singleton;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:43 2018/5/16
 * @Description
 */

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 缺点：
 * 1.可能会造成资源的浪费
 * 2.加载内容过多时，加载速度慢
 * */

@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    public static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
