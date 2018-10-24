package com.bruce.thread.aqs;

import com.bruce.thread.ThreadDemo07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AqsLockTest {

    private AqsLock mLock = new AqsLock();

    private int mValue = 0;

    private void next() {
        mLock.lock();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mValue ++;
        System.out.println(mValue);
        mLock.unlock();
    }


    private void a() {
        mLock.lock();
        System.out.println("a");
        b();
        mLock.unlock();
    }

    private void b() {
        mLock.lock();
        System.out.println("b");
        c();
        mLock.unlock();
    }

    private void c() {
        mLock.lock();
        System.out.println("c");
        mLock.unlock();
    }


    public static void main(String[] args) {
        AqsLockTest test = new AqsLockTest();
        ExecutorService es = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 1000; i++) {
//            es.execute(test::next);
//            es.execute(test::next);
//            es.execute(test::next);
//            es.execute(test::next);
//        }

        es.execute(test::a);
        es.shutdown();
    }
}
