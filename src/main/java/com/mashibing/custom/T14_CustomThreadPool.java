package com.mashibing.custom;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * <p>功能描述：自定义线程池</p>
 * <ul>线程池中的线程工厂自定义，任务队列用LinkedBlockingQueue
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/5/10 15:25</li>
 * </ul>
 */
public class T14_CustomThreadPool {

    /*最大并发数, cup核的两倍*/
    private static final int MAX_CONCURRENT = Runtime.getRuntime().availableProcessors()*2;

    /*默认队列大小*/
    private static final int DEFAULT_SIZE = 10;

    /*默认线程存活时间*/
    private static final long DEFAULT_KEEP_ALIVE = 60L;

    /*任务执行队列*/
    private static BlockingQueue queue = new ArrayBlockingQueue(DEFAULT_SIZE);

    private static ExecutorService executorService = null;
    private static URL url = null;

    static {
        try {
            url = new URL("https://www.baidu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        /*创建线程池对象*/
        executorService = new ThreadPoolExecutor(
                MAX_CONCURRENT, MAX_CONCURRENT * 4, DEFAULT_KEEP_ALIVE,
                TimeUnit.SECONDS, queue, new MyThreadFactory(" test "), new MyRejectedPolicy("testThread", url));
    }


    public static void main(String[] args) {
        try {
//            for (int i = 0; i < 100; i++) {

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ThreadName：" + Thread.currentThread().getName() + "进来了");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("ThreadName：" + Thread.currentThread().getName() + "出去了");
                    }
                };
                executorService.execute(runnable);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
