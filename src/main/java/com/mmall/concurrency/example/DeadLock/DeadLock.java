package com.mmall.concurrency.example.DeadLock;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 20:02 2018/5/25
 * @Description
 */
public class DeadLock implements Runnable {

    public int flag = 1;
    //静态对象是类的所有对象的共享，o1与o2的争夺满足必要条件之一，互斥
    //满足不可剥夺条件，进程所获得的资源在未使用完毕之前，不被其他进程强行剥夺
    //请求和保持条件：进程每次申请它所需要的一部分资源，在申请新的资源的同时，继续占用已分配到的资源
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag:" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    //sleep500毫秒的原因就是若flag=1线程先执行了，等待flag=0
                    //若flag=0先执行，等待flag=1线程让其满足循环等待条件
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock example1 = new DeadLock();
        DeadLock example2 = new DeadLock();
        example1.flag = 1;
        example2.flag = 0;
        //两线程都可以执行，但是不能确定哪个先执行
        new Thread(example1).start();
        new Thread(example2).start();
    }
}
