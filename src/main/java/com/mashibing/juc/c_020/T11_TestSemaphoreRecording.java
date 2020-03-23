package com.mashibing.juc.c_020;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述：Semaphore是信号量，抢到信号量就执行，抢不到就阻塞</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/23 22:43</li>
 * </ul>
 */
public class T11_TestSemaphoreRecording {
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1=====1");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1=====2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2-------1");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T2-------2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
