package com.mashibing.juc.c_012_Volatile;

/**
 * <p>功能描述：单例模式双重检查</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2020/3/6 8:35</li>
 * </ul>
 */
public class DoubleCheckLock {

    /*禁止指令重排序 volatile */
    private static volatile DoubleCheckLock doubleCheckLock;

    /*单例模式不能从构造方法创建对象*/
    private DoubleCheckLock() {

    }

    /*懒汉式单例模式，双重检查*/
    public static DoubleCheckLock getHungryInstance() {
        if (null == doubleCheckLock) {
            synchronized (DoubleCheckLock.class) {
                if (null == doubleCheckLock) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doubleCheckLock = new DoubleCheckLock();
                }
            }
        }
        return doubleCheckLock;
    }


    private static DoubleCheckLock dc;

    public static DoubleCheckLock getInstance() {
        if (null == dc) {
            synchronized (DoubleCheckLock.class) {
                if (null == dc) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dc = new DoubleCheckLock();
                }
            }
        }
        return dc;
    }

    public static void m() {
        DoubleCheckLock lock = getInstance();
        System.out.println(lock.hashCode());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DoubleCheckLock lock = getInstance();
                    System.out.println(lock.hashCode());
                }
            }).start();
        }
    }
}
