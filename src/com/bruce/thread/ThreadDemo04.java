package com.bruce.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized的锁对象是类对象。
 * 如果对象获取a的锁，且a调用了b，那么对象能直接进入方法b，者叫锁重入
 */
public class ThreadDemo04 {

    public synchronized void a() {

        System.out.println("a");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b();
    }

    public synchronized void b() {
        try {
            Thread.sleep(110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "----> b");
    }


    public static void main(String[] args) {
        ThreadDemo04 td1 = new ThreadDemo04();
        ThreadDemo04 td2 = new ThreadDemo04();

        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(td1::a);
        es.execute(td2::b);
        es.shutdown();
    }

}
