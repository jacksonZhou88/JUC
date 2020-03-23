package com.mashibing.juc.c_018_00_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>功能描述：CAS\Atomic\LongAdder</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/14 18:17</li>
 * </ul>
 */
public class CASVSAtomicVSLongAdder {

    private static long k = 0L;
    private static AtomicLong atomicInteger = new AtomicLong(0L);
    private static LongAdder longAdder = new LongAdder();

    /*synchronized自增*/
//    public synchronized void m() {
//        for (int i = 0; i < 100000; i++) {
//            k++;
//        }
//    }

//    /*atomicInteger自增*/
//    public static void atomicIntegerIncrease() {
//        for (int i = 0; i < 100000; i++) {
//            atomicInteger.incrementAndGet();
//        }
//    }
//
//    /*longAdder自增*/
//    public static void longAdderIncrease() {
//        for (int i = 0; i < 100000; i++) {
//            longAdder.increment();
//        }
//    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[1000];

        /*1. AtomicInteger*/
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    atomicInteger.incrementAndGet();
                }

            });
        }

        long t3 = System.currentTimeMillis();

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        long t4 = System.currentTimeMillis();
        System.out.println("AtomicInteger: " + atomicInteger.get() + " 耗时：" + (t4 - t3));

        /*2. synchronized */
        Object object = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        synchronized (object) {
                            k++;
                        }
                    }
                }
            });
        }

        long t1 = System.currentTimeMillis();

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        long t2 = System.currentTimeMillis();
        System.out.println("synchronized: " + k + " 耗时：" + (t2 - t1));

        /*3. LongAdder*/
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    longAdder.increment();
                }

            });
        }
        long t5 = System.currentTimeMillis();

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        long t6 = System.currentTimeMillis();
        System.out.println("longAdder: " + longAdder.intValue() + " 耗时：" + (t6 - t5));
    }
}
