package com.mashibing.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>功能描述：生产者和消费者，同步容器，定向的去唤醒线程</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/27 21:18</li>
 * </ul>
 */
public class MyContainer2Recording {
    private LinkedList list = new LinkedList();
    private Integer count = 0;
    private Integer MAX = 10;

    private Lock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public void put(Object object) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(object);
            count++;
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object get() {
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            Object object = list.removeFirst();
            count--;
            producer.signalAll();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyContainer2Recording recording = new MyContainer2Recording();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    Object object = recording.get();
                    System.out.println("消费者："+object);
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    recording.put(j);
                    System.out.println("生产者："+j);
                }
            }).start();
        }
    }
}
