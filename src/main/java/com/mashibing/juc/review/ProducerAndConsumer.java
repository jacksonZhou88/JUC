package com.mashibing.juc.review;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述：生产者和消费者问题：一个固定容量的容器，get()方法、put()方法、
 * getCount()方法 2个生产者线程  10个消费者线程，实现阻塞调用，用wait()和notify()方法< /p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/4/19 10:01</li>
 * </ul>
 */
public class ProducerAndConsumer {

    List list = new ArrayList<>();

    int MAX = 10;

    int count = 0;

    public synchronized void get() {
        while (list.size() == 0) {
            try {
                wait();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        list.remove(0);
        count--;
        System.out.println(Thread.currentThread().getName() + " removing " + list.size());
        notify();
    }

    public synchronized void put() {
        while (list.size() == MAX) {
            try {
                wait();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        list.add(new Object());
        count++;
        System.out.println(Thread.currentThread().getName() + " adding " + list.size());
        notify();
    }

    public static void main(String[] args) {
        ProducerAndConsumer pAc = new ProducerAndConsumer();
        Thread[] getThreads = new Thread[2];
        Thread[] putThreads = new Thread[10];
        for (int i = 0; i < 2; i++) {
            getThreads[i] = new Thread(pAc::get);
        }

        for (int i = 0; i < 10; i++) {
            putThreads[i] = new Thread(pAc::put);
        }
        for (Thread thread : getThreads) thread.start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (Thread thread : putThreads) thread.start();
    }
}
