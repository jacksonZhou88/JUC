package com.mashibing.juc.c_025;

import java.util.PriorityQueue;

public class T07_01_PriorityQueque {
    public static void main(String[] args) {
        PriorityQueue<String> blockingQueue = new PriorityQueue<>();
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);

        blockingQueue.add("a");
        blockingQueue.add("c");
        blockingQueue.add("e");
        blockingQueue.add("d");
        blockingQueue.add("b");
        System.out.println(blockingQueue);
        String head = blockingQueue.poll();
        System.out.println(blockingQueue);
        System.out.println(head);

//        q.add("c");
//        q.add("e");
//        q.add("a");
//        q.add("d");
//        q.add("z");

//        System.out.println(q);
//        for (int i = 0; i < 5; i++) {
//            System.out.println(q.poll());
//        }

    }
}
