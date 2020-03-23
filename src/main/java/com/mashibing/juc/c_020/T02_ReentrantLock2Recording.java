package com.mashibing.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>功能描述：ReenTrantLock可重入锁，手动上锁，手动解锁</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/22 15:27</li>
 * </ul>
 */
public class T02_ReentrantLock2Recording {
    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void m2(){
         try {
             lock.lock();
             System.out.println("m2...");
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2Recording recording = new T02_ReentrantLock2Recording();
        new Thread(recording::m1, "").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(recording::m2, "").start();
    }
}
