package com.mmall.concurrency.example.Singleton;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 11:43 2018/5/16
 * @Description
 */

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * 指令重排导致线程不安全
 * */

@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    // 1.memory = allocate 分配对象的内存空间
    // 2.ctorInstance() 初始化对象
    // 3.instance = memory 设置instance指向刚分配的内存


    // JVM和CPU优化，发生了指令重排
    // 1.memory = allocate 分配对象的内存空间
    // 3.instance = memory 设置instance指向刚分配的内存
    // 2.ctorInstance() 初始化对象

    // 单例对象
    public static SingletonExample4 instance = null;

    // 静态工厂方法
    public static  SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制  线程2走到这一步后instance不为null，直接返回出现错误
            synchronized (SingletonExample4.class) {  //同步锁
                if (instance == null) {
                    instance = new SingletonExample4();  //线程1执行了重排后的3，但并没有初始化对象
                }
            }
        }
        return instance;
    }
}
