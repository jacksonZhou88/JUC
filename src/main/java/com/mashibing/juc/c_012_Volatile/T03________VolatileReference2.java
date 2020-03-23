/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 *
 * 答案：因为线程在读a的值的时候是一个线程的值，在读b的时候变成了另外一个线程的值了。
 */
package com.mashibing.juc.c_012_Volatile;

public class T03________VolatileReference2 {

    private static class Data {
        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    volatile static Data data;

    public static void main(String[] args) {
        Object o = new Object();
        Thread writer = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                synchronized (o) {
                    data = new Data(i, i);
                }
            }
        });

        Thread reader = new Thread(()->{
            while (data == null) {}
            synchronized (o) {
                int x = data.a;
                int y = data.b;
                if(x != y) {
                    System.out.printf("a = %s, b=%s%n", x, y);
                }

            }
        });

        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
