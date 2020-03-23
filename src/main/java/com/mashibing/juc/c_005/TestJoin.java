package com.mashibing.juc.c_005;

/**
 * <p>功能描述：join方法会释放调用join的线程的锁</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/5 8:19</li>
 * </ul>
 */
public class TestJoin {

    public static void main(String[] args) {
        Object object = new Object();
        MyThread myThread = new MyThread("thread1", object);
        myThread.start();


        synchronized (myThread) {
            for (int i = 0; i < 100; i++) {
                if (i==20) {
                    try {
                        myThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"======"+i);
            }
        }
    }

    static class MyThread extends Thread {
        private String name;
        private Object object;

        MyThread(String name, Object object) {
            this.name = name;
            this.object = object;
        }

        public void run() {
            synchronized (this) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(name+"======="+i);
                }
            }
        }
    }

}
