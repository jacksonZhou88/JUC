package com.mashibing.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>功能描述：自定义阻塞队列，本质就是生产者和消费者</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/7/3 22:52</li>
 * </ul>
 */
public class LinkedBlockingQueue {

    LinkedList list = new LinkedList();
    int count = 0;
    int MAX = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    /*阻塞队列塞值*/
    public void put(Object object) {
        try {
            lock.lock();
            while (count >= MAX) {
                producer.await();
            }
            list.add(object);
            count++;
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object getObject(){
         try {
             lock.lock();
             if (count <= 0) {
                 consumer.await();
             }
             count--;
             Object obj = list.pop();
             producer.signalAll();
             return obj;
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
         return null;
    }
}
