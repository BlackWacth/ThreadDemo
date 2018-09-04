package com.bruce.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class ThreadDemo03 {


    //带返回值和异常的线程创建方式。
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int result = add(list);
        System.out.println("计算结果: result = " + result);
    }

    public static int add(List<Integer> list) {
        System.out.println("***************** parallelStream ***********************");
        list.parallelStream().forEach(System.out::println);
        System.out.println("***************** parallelStream ***********************");

        System.out.println("\n\n***************** stream ***********************");
        list.stream().forEach(System.out::println);
        System.out.println("***************** stream ***********************");

        //lambda方式进行并行计算
        return list.parallelStream().mapToInt(a -> a).sum();
    }

}
