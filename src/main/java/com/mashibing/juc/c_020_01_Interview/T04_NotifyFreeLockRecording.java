package com.mashibing.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述：线程通信：wait()和 notify(),wait()自己进入等待状态并释放锁，notify()通知线程复活，不释放锁 </p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/24 23:50</li>
 * </ul>
 */
public class T04_NotifyFreeLockRecording {
    static List list = new ArrayList();

    public void add(Object object) {
        list.add(object);
    }

    public int get() {
        return list.size();
    }

    final static Object lock = new Object();

    public static void main(String[] args) {

        //监控线程
        new Thread(() -> {
            System.out.println("T2监控线程启动===");
            synchronized (lock) {
                if (list.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2被唤醒了！");
                lock.notify();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //添加元素线程
        new Thread(() -> {
            System.out.println("T1生产线程启动===");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println("add==="+i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
