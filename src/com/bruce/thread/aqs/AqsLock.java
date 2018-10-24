package com.bruce.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AqsLock implements Lock {

    private AqsHelper mHelper = new AqsHelper();

    @Override
    public void lock() {
        mHelper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mHelper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mHelper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mHelper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        mHelper.release(1);
    }

    @Override
    public Condition newCondition() {
        return mHelper.newCondition();
    }

    private class AqsHelper extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {

            if (getState() == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                if(getExclusiveOwnerThread() == Thread.currentThread()) {
                    setState(getState() + arg);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new RuntimeException();
            }
            int state = getState() - arg;
            boolean flag = false;
            if (state == 0) {
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);
            return flag;
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }
}
