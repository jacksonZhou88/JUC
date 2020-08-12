package com.mashibing.juc.c_023_02_FromHashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;

public class T01_TestHashtableRecording {

    static Integer count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static int gap = count / Constants.THREAD_COUNT;
    static Hashtable hashtable = new Hashtable();

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        private int start;

        public MyThread(int start) {
            this.start = start;
        }

        public void run() {
            for (int i = start; i < start + gap; i++) {
                hashtable.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        Thread[] threads = new Thread[Constants.THREAD_COUNT];
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            threads[i] = new MyThread(i*gap);
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println("ÈÝÆ÷ÈÝÁ¿£º" + hashtable.size() + "Ð´²Ù×÷ÏûºÄÁË£º"+(t2-t1)+"ºÁÃë");

        /*------------------hashtable¶Á²Ù×÷------------------*/
        long t3 = System.currentTimeMillis();
        threads = new Thread[Constants.THREAD_COUNT];
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < gap; j++) {
                    hashtable.get(keys[j]);
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t4 = System.currentTimeMillis();
        System.out.println("¶Á²Ù×÷ÏûºÄÁË£º"+(t4 - t3));
    }

}
