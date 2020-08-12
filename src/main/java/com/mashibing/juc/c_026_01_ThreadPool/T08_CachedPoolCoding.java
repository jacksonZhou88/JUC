package com.mashibing.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T08_CachedPoolCoding {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            final int j = i;
            executorService.execute(()->{
                System.out.println("ÐòºÅ£º"+j+" Ïß³ÌÃû³Æ£º"+Thread.currentThread().getName());
            });
        }
    }
}
