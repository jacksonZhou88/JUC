package com.mashibing.juc.c_020;

import java.util.concurrent.Exchanger;

public class T12_TestExchangerRecording {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            System.out.println("this thread name is "+ Thread.currentThread().getName() + " s="+s);
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this thread name is "+ Thread.currentThread().getName() + " s="+s);
        }).start();

        new Thread(() -> {
            String s = "T2";
            System.out.println("this thread name is "+ Thread.currentThread().getName() + " s="+s);
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this thread name is "+ Thread.currentThread().getName() + " s="+s);
        }).start();
    }
}
