package com.mmall.concurrency.example.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 16:48 2018/5/14
 * @Description
 */

@Slf4j
public class LockExample3 {

    private final Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final static Lock readLock = lock.readLock();

    private final static Lock writeLock = lock.writeLock();

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }

}
