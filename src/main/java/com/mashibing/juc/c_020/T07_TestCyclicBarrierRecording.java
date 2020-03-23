package com.mashibing.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
*<p>功能描述：比countDownLatch更方便，也是相当于一个栅栏，等到数量等于容量值，打开栅栏，程序执行</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/3/22 17:03</li>
*</ul>
*/
public class T07_TestCyclicBarrierRecording {

    static CyclicBarrier barrier = new CyclicBarrier(20, ()->{
        System.out.println("人满啦，出发！");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
