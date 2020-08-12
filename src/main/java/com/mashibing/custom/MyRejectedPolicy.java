package com.mashibing.custom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>功能描述：自定义拒绝策略</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/5/13 7:35</li>
 * </ul>
 */
public class MyRejectedPolicy extends ThreadPoolExecutor.AbortPolicy {

    private String threadName;

    private URL url;

    private static volatile long lastPrintTime = 0;

    private static Semaphore guard = new Semaphore(1);

    public MyRejectedPolicy(String threadName, URL url) {
        this.threadName = threadName;
        this.url = url;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format("Thread pool is EXHAUSTED!" +
                        " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), in %s://%s:%d!",
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating(),
                url.getProtocol(), url.getHost(), url.getPort());
        dumpJStack();
        throw new RejectedExecutionException(msg);
    }

    private void dumpJStack() {


        long now = System.currentTimeMillis();

        //dump every 10 minutes
        //每 10 分钟，只打印一次。
        if (now - lastPrintTime < 10 * 60 * 1000) {
            return;
        }
        //获得信号量。保证，同一时间，有且仅有一个线程执行打印。
        if (!guard.tryAcquire()) {
            return;
        }
        //// 创建线程池，后台执行打印 JStack
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                // 获得路径
//                String dumpPath = url.getParameter(Constants.DUMP_DIRECTORY, System.getProperty("user.home"));
                String dumpPath = "";

                SimpleDateFormat sdf;
                // 获得系统
                String OS = System.getProperty("os.name").toLowerCase();

                // window system don't support ":" in file name
                if (OS.contains("win")) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                }

                String dateStr = sdf.format(new Date());
                // 获得输出流
                FileOutputStream jstackStream = null;
                try {
                    jstackStream = new FileOutputStream(new File(dumpPath, "Dubbo_JStack.log" + "." + dateStr));
                    // 打印 JStack
                    JVMUtil.jstack(jstackStream);
                } catch (Throwable t) {
                    System.out.println(t);
                } finally {
                    // 释放信号量
                    guard.release();
                    // 释放输出流
                    if (jstackStream != null) {
                        try {
                            jstackStream.flush();
                            jstackStream.close();
                        } catch (IOException e) {
                        }
                    }
                }
                // 记录最后打印时间
                lastPrintTime = System.currentTimeMillis();
            }
        });

    }
}
