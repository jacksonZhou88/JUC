package com.mashibing.custom;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

    private String namePrefix;
    private AtomicInteger threadIndex = new AtomicInteger(1);

    public MyThreadFactory(String whatFeartherOfGroup){
        namePrefix = "From UserThreadFactory's " + whatFeartherOfGroup + "-worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + threadIndex.incrementAndGet();
        Thread thread = new Thread(r, name);
//        System.out.println(thread.getName());
        return thread;
    }
}
