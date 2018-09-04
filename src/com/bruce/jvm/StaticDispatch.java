package com.bruce.jvm;

/**
 * java 静态分配(Static Dispatch)
 * 静态分派的典型应用是方法重载overlord。
 * 静态分派指的是在编译期间进行的方法选择，通常以方法名称，方法接收者和方法参数的静态类型来作为方法选择的依据。
 */
public class StaticDispatch {

    class Human {

    }

    class Man extends Human {

    }

    class Women extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, man!");
    }

    public void sayHello(Women guy) {
        System.out.println("hello, women!");
    }

    public void printSayHello() {
        Human man = new Man();
        Human women = new Women();
        sayHello(man);
        sayHello(women);
    }

    public static void main(String[] args) {

        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.printSayHello();

    }

}
