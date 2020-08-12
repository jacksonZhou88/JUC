package com.mashibing.juc.homeWork;

public class HomeWork01 {

    public synchronized void m() {
        for (int i = 65; i < 91; i++) {
            try {
                char c = (char) i;
//                TimeUnit.SECONDS.sleep(1);
                System.out.print(c);
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
        }
    }

    public synchronized void m2() {
        for (int i = 1; i < 27; i++) {
            try {
//                TimeUnit.SECONDS.sleep(1);
                System.out.print(i);
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
        }
    }

    public static void main(String[] args) {
        HomeWork01 work01 = new HomeWork01();
        new Thread(work01::m2, "thread2").start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(work01::m, "thread1").start();
    }
}
