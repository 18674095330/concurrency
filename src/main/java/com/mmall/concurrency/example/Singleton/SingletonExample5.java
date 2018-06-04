package com.mmall.concurrency.example.Singleton;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:43 2018/5/16
 * @Description
 */


import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * volatile可以防止发生指令重排
 * */

@ThreadSafe
public class SingletonExample5 {

    // 私有构造函数
    private SingletonExample5() {

    }

    // 1.memory = allocate 分配对象的内存空间
    // 2.ctorInstance() 初始化对象
    // 3.instance = memory 设置instance指向刚分配的内存


    // JVM和CPU优化，发生了指令重排
    // 1.memory = allocate 分配对象的内存空间
    // 3.instance = memory 设置instance指向刚分配的内存
    // 2.ctorInstance() 初始化对象

    // 单例对象 volatile + 双重检测机制 ——> 禁止指令重排
    public static volatile SingletonExample5 instance = null;

    // 静态工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample5.class) {  //同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
