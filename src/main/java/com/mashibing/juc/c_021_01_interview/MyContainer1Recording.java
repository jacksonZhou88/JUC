package com.mashibing.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p>���������������ߺ������ߣ�����һ��ͬ��������֧��10���������߳����ģ�5���������߳�����</p>
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

    /*����������*/
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
        System.out.println("�������̣߳�"+count);
        this.notifyAll();
    }

    /*����������*/
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
        System.out.println("�������̣߳�"+count);
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
            /*�������߳�*/
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
            /*�������߳�*/
//            new Thread(recording::put, "productThread"+i).start();
        }
    }
}
