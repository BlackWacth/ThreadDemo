package com.bruce.jvm;

/**
 * 动态分配(Dynamic Dispatch)
 * 动态分派的典型应用是方法重写override。
 * 动态分派是指方法的确定在run-time才能最终完成。
 */
public class StaticDispatch01 {

    static abstract class Human {
        public abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        public void sayHello() {
            System.out.println("hello, man!");
        }
    }

    static class Women extends Human {

        @Override
        public void sayHello() {
            System.out.println("hello, women!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();

        man.sayHello();
        women.sayHello();

    }

}
