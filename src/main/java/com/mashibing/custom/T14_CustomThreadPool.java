package com.mashibing.custom;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * <p>�����������Զ����̳߳�</p>
 * <ul>�̳߳��е��̹߳����Զ��壬���������LinkedBlockingQueue
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/5/10 15:25</li>
 * </ul>
 */
public class T14_CustomThreadPool {

    /*��󲢷���, cup�˵�����*/
    private static final int MAX_CONCURRENT = Runtime.getRuntime().availableProcessors()*2;

    /*Ĭ�϶��д�С*/
    private static final int DEFAULT_SIZE = 10;

    /*Ĭ���̴߳��ʱ��*/
    private static final long DEFAULT_KEEP_ALIVE = 60L;

    /*����ִ�ж���*/
    private static BlockingQueue queue = new ArrayBlockingQueue(DEFAULT_SIZE);

    private static ExecutorService executorService = null;
    private static URL url = null;

    static {
        try {
            url = new URL("https://www.baidu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        /*�����̳߳ض���*/
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
                        System.out.println("ThreadName��" + Thread.currentThread().getName() + "������");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("ThreadName��" + Thread.currentThread().getName() + "��ȥ��");
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
