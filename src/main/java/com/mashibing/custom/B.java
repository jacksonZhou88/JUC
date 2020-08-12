package com.mashibing.custom;

public class B {


    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(new Object());
        threadLocal.get();
        Thread thread = Thread.currentThread();
    }

}
