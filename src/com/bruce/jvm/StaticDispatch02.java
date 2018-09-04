package com.bruce.jvm;

public class StaticDispatch02 {

    class Person {
        int age = 50;

        public int getAge() {
            return age;
        }
    }

    class Son extends Person {
        int age = 25;


        public int getAge() {
            return age;
        }
    }

    void print() {
        Person ps = new Son();
        System.out.println("ps.age = " + ps.age);
        System.out.println("ps.getAge() = " + ps.getAge());
    }

    public static void main(String[] args) {
        StaticDispatch02 staticDispatch02 = new StaticDispatch02();
        staticDispatch02.print();
    }
}
