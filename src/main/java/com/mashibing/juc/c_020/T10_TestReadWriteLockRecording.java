package com.mashibing.juc.c_020;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
*<p>功能描述：读写锁：读锁是共享锁，允许多个线程一起读，写锁是排他锁，只允许写线程执行，其他线程等着</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/3/23 7:42</li>
*</ul>
*/
public class T10_TestReadWriteLockRecording {
    private static Integer value = new Integer(0);
    static Lock lock = new ReentrantLock();

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
         try {
             lock.lock();
             TimeUnit.SECONDS.sleep(1);
             System.out.println("read over...");
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

    public static void write(Lock lock, Integer val) {
         try {
             lock.lock();
             TimeUnit.SECONDS.sleep(1);
             value = val;
             System.out.println("write over!");
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

    public static void main(String[] args) {

//        Runnable read = () -> read(lock);
        Runnable read = () -> read(readLock);
//        Runnable write = () -> write(lock, new Random(10).nextInt());
        Runnable write = () -> write(writeLock, new Random(10).nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(read).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }
    }
}
