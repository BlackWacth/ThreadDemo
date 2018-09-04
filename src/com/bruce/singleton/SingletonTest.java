package com.bruce.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            es.execute(() -> System.out.println("当前线程：" + Thread.currentThread() + " --> Singleton = " + Singleton.getInstance()));
        }
        es.shutdown();
    }

}
