package com.mashibing.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T09_FixedThreadPool_coding {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 15; i++) {
            final int j = i;
            executorService.execute(()->{
                System.out.println("ÐòºÅ£º"+j+" Ïß³ÌÃû£º"+Thread.currentThread().getName());
            });
        }
    }
}
