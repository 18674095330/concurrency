package com.mmall.concurrency.example.Singleton;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:43 2018/5/16
 * @Description
 */

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式的另外一种写法
 * 单例实例在类装载时进行创建
 * 缺点：
 * 1.可能会造成资源的浪费
 * 2.加载内容过多时，加载速度慢
 * */

@ThreadSafe
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {

    }

    // 单例对象
    public static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
