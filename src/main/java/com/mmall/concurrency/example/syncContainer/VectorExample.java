package com.mmall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 17:10 2018/5/21
 * @Description
 */
public class VectorExample {

    //foreach
    private static void test1(Vector<Integer> v) {
        for (Integer i : v) {
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    //iterator
    private static void test2(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    //for
    private static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(v.get(i));
            }
        }
    }

    private static void test4(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test4(vector);
        System.out.println(vector);
    }
}
