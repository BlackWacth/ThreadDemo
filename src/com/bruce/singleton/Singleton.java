package com.bruce.singleton;

public class Singleton {

    public static volatile Singleton sInstance;

    private Singleton() { }

    public static Singleton getInstance() {
        if (sInstance == null) {
            synchronized (Singleton.class) {
                if (sInstance == null) {
                    sInstance = new Singleton();
                }
            }
        }
        return sInstance;
    }
}
