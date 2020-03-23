package com.mashibing.juc.c_020;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
*<p>功能描述：countDownLatch：就是一个栅栏，等到数量到达指定的数字时，就把栅栏打开，程序顺利往下运行</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/3/22 16:26</li>
*</ul>
*/
public class T06_TestCountDownLatchRecording {

    private static AtomicInteger threadCount = new AtomicInteger(0);
    private static AtomicInteger threadCount2 = new AtomicInteger(0);

    public static void testJoin() throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                threadCount.incrementAndGet();
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        System.out.println("joinThreadCount: " + threadCount);
    }

    public static void testCountDown(){
        Thread[] threads = new Thread[100];
        CountDownLatch lock = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                threadCount2.incrementAndGet();
                lock.countDown();
            });
        }
        try {
            for (Thread thread : threads) {
                thread.start();
            }
            lock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("countDownLatch："+threadCount2);

    }

    public static void main(String[] args) throws InterruptedException {
        testJoin();
        testCountDown();
    }
}
