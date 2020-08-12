package com.mashibing;

public class JustTest {

//    public static void main(String[] args) {
//        Thread[] threads = new Thread[11];
//
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new Thread(()->{
//                System.out.println(Thread.currentThread().getName() + " count");
//            });
//        }
//
//        List list = new ArrayList();
//        threads[10] = new Thread(()->{
//            while(true){
//                try {
//                    TimeUnit.MICROSECONDS.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                list.add(new Object());
//            }
//        }, "deadLock");
//
//        for (Thread thread: threads) thread.start();
//
//
//    }

    public synchronized void m() {
        System.out.println(111);
    }

    static class myThread implements Runnable {
        private static Object o1 = new Object();
        private static Object o2 = new Object();

        @Override
        public void run() {
            synchronized (o1) {
                System.out.println("get o1");
                synchronized (o2) {
                    System.out.println("get o2");
                }
            }
        }

    }


    static class myThread2 implements Runnable {
        private static Object o1 = new Object();
        private static Object o2 = new Object();

        @Override
        public void run() {
            synchronized (o2) {
                System.out.println("get o2");
                synchronized (o1) {
                    System.out.println("get o1");
                }
            }
        }

    }

    public static void main(String[] args) {
        myThread m1 = new myThread();
        myThread2 m2 = new myThread2();

        /*死锁问题*/
        Thread thread1 = new Thread(m1);
        Thread thread2 = new Thread(m2);
        thread1.start();
        thread2.start();
    }
}
