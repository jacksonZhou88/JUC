package com.mashibing.juc.c_020;
/**
*<p>功能描述：synchronized是可重入的，调用m2需要获取锁，m1还在执行没有释放锁，
 * 但是苗m2也得到了执行，所以synchronized是可重入的</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/3/22 14:54</li>
*</ul>
*/
import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock1Recoding {
    public synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) {
                m2();
            }
        }
    }

    public synchronized void m2(){
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1Recoding record = new T01_ReentrantLock1Recoding();
        new Thread(record::m1, "").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(record::m2, "").start();
    }
}
