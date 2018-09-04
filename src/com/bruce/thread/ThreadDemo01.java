package com.bruce.thread;

public class ThreadDemo01 {

    public static void main(String[] args) {

        //执行重载的run方法，也就是输出 "执行的是内部 run "
        Thread thread = new Thread(() -> System.out.println("执行的是Runnable")){
            @Override
            public void run() {
                System.out.println("执行的是内部 run ");
            }
        };
        thread.start();
    }

}
