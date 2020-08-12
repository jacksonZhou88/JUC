package com.mashibing.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述：生产者和消费者，创建一个同步容器，支持10个消费者线程消耗，5个生产者线程生产</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/27 20:30</li>
 * </ul>
 */
public class MyContainer1Recording {

    private LinkedList list = new LinkedList();
    private Integer MAX = 10;
    private Integer count = 0;

    /*生产者生产*/
    public synchronized void put() {
        while (count == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object object = new Object();
        list.add(object);
        count++;
        System.out.println("生产者线程："+count);
        this.notifyAll();
    }

    /*消费者消耗*/
    public synchronized void get(){
        while(count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.removeFirst();
        count--;
        System.out.println("消费者线程："+count);
        this.notifyAll();
    }

    public static void main(String[] args) {
        MyContainer1Recording recording = new MyContainer1Recording();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    recording.get();
                }
            }).start();
            /*消费者线程*/
//            new Thread(recording::get, "consumerThread"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    recording.put();
                }
            }).start();
            /*生产者线程*/
//            new Thread(recording::put, "productThread"+i).start();
        }
    }
}
