package com.mashibing.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>功能描述：ReentrantLock可以尝试加锁，可以根据是否拿到锁的结果执行不同的业务代码</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/22 15:41</li>
 * </ul>
 */
public class T03_ReentrantLock3Recording {
    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    boolean locked = false;

    public void m2() {
        try {
            long t1 = System.currentTimeMillis();
            System.out.println("======start========");
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            long t2 = System.currentTimeMillis();
            long tt = t2-t1;
            System.out.println(locked+"======end========"+tt);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("locked===" + locked);
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3Recording recording = new T03_ReentrantLock3Recording();
        new Thread(recording::m1, "").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(recording::m2, "").start();
    }
}
