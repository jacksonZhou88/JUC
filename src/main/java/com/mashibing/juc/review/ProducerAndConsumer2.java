package com.mashibing.juc.review;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>功能描述：生产者和消费者，用condition实现< /p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/4/19 11:44</li>
 * </ul>
 */
public class ProducerAndConsumer2 {
    private List list = new ArrayList();
    private Integer MAX = 10;
    Lock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public synchronized void put() {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(new Object());
             System.out.println("adding " + list.size());
            consumer.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public synchronized void get() {
         try {
             lock.lock();
             while(list.size() == 0){
                 consumer.await();
             }
             list.remove(0);
             System.out.println("removing " + list.size());
             producer.signalAll();
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

    public static void main(String[] args) {
        ProducerAndConsumer2 pac2 = new ProducerAndConsumer2();
        Thread[] cth = new Thread[2];
        for (int i = 0; i < cth.length; i++) {
            cth[i] = new Thread(pac2::get);
        }

        Thread[] pth = new Thread[10];
        for (int i = 0; i < pth.length; i++) {
            pth[i] = new Thread(pac2::put);
        }

        for (Thread thread : pth) thread.start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (Thread thread : cth) thread.start();
    }
}
