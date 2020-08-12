package com.mashibing.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
*<p>功能描述： 通过门栓来控制两个线程</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/3/27 7:52</li>
*</ul>
*/
public class T05_CountDownLatchRecording {
    private static List list = new ArrayList<>();

    public static Integer size(){
        return list.size();
    }

    public static void put(Object object){
        list.add(object);
    }

    /*门栓*/
    static CountDownLatch latch = new CountDownLatch(1);
    static CountDownLatch latch2 = new CountDownLatch(1);

    public static void main(String[] args) {
        //监控线程
        new Thread(() -> {
            if (list.size() != 5){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get5=====监控线程执行完毕=====");
                latch2.countDown();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //减少对象线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println("add="+i);
                if (list.size() == 5) {
                    latch.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
