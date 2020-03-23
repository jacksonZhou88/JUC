package com.mashibing.juc.c_000;

public class T02_HowToCreateThread {
    static class MyThread extends Thread{
         public void run(){
             System.out.println("MyThread is running!");
         }
    }

    static class implementThread implements Runnable{

        @Override
        public void run() {
            System.out.println("I'm implements runnable!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new implementThread()).start();
        new Thread(){
            public void run(){
                System.out.println("nomal method to new Thread");
            }
        }.start();
        new Thread(() -> System.out.println("lambde way to new Thread!")).start();

    }
}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
