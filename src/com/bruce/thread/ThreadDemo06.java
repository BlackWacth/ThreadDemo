package com.bruce.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁：两个线程相互等待
 * 可以用jconsole(java自带)工具检测是否存在死锁
 */
public class ThreadDemo06 {

    private int value = 0;
    private Lock lock = new ReentrantLock();

    public int getValue() {
        lock.lock();
        value += 1;
        lock.unlock();
        return value;
    }

    public static void main(String[] args) {
        ThreadDemo06 td = new ThreadDemo06();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 200; i++) {
            es.execute(() -> System.out.println(Thread.currentThread() + " --> " + td.getValue()));
            es.execute(() -> System.out.println(Thread.currentThread() + " --> " + td.getValue()));
            es.execute(() -> System.out.println(Thread.currentThread() + " --> " + td.getValue()));
            es.execute(() -> System.out.println(Thread.currentThread() + " --> " + td.getValue()));
            es.execute(() -> System.out.println(Thread.currentThread() + " --> " + td.getValue()));
        }
        es.shutdown();
    }

}
