package com.bruce.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 死锁：两个线程相互等待
 * 可以用jconsole(java自带)工具检测是否存在死锁
 */
public class ThreadDemo05 {

    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    public void a() {
        synchronized(obj1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(obj2) {
                System.out.println("a");
            }
        }
    }

    public void b() {
        synchronized(obj2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(obj1) {
                System.out.println("b");
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo05 td = new ThreadDemo05();
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(td::a);
        es.execute(td::b);
        es.shutdown();
    }

}
