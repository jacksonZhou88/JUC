package com.mashibing.juc.c_012_Volatile;

public class JsutTest {

    private static int k;

    public void mm() {
        for (int i = 0; i < 100; i++) {
            k++;
            System.out.println("thread===" + k);
        }

    }

    public static void main(String[] args) {
        JsutTest jsutTest = new JsutTest();
        for (int i = 0; i < 100; i++) {
            new Thread(jsutTest::mm, "thread==" + i).start();
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    jsutTest.mm();
//                }
//            });
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
