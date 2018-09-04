package com.bruce.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 死锁：两个线程相互等待
 * 可以用jconsole(java自带)工具检测是否存在死锁
 */
public class ThreadDemo07 {

    MyLock mLock = new MyLock();

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
        ThreadDemo07 td = new ThreadDemo07();
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(td::a);
        es.execute(td::b);
        es.shutdown();
    }


    class MyLock implements Lock {

        private Thread mCurrentThread;
        private int mThreadCount = 0;

        @Override
        public synchronized void lock() {
            Thread thread = Thread.currentThread();
            while (thread != mCurrentThread && mThreadCount > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mCurrentThread = thread;
            mThreadCount++;
        }

        @Override
        public synchronized void unlock() {
            if (mCurrentThread == Thread.currentThread()) {
                mThreadCount --;
                if (mThreadCount == 0) {
                    notify();
                }
            } else {
                notify();
            }

        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}

