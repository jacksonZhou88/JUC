package com.mashibing.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool_coding {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.execute(() -> {
                System.out.println(j + "singleThreadExecutor");
            });
        }
        executorService.shutdown();
    }
}
