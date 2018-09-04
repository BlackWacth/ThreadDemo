package com.bruce.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo02 {


    //带返回值和异常的线程创建方式。
    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>(new MCallable());

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("先做别的事情...");
        try {
            Integer result = task.get();
            System.out.println("计算结果: result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class MCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("正在计算...");
            return (int)(Math.random() * 100);
        }
    }

}
