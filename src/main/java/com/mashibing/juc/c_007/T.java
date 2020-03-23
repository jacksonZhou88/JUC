/**
 * 同步和非同步方法是否可以同时调用？
 * @author mashibing
 */

package com.mashibing.juc.c_007;

public class T {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}

	public void m2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2 ");
	}

	public static void main(String[] args) {
		T t = new T();

		/*new Thread(()->t.m1(), "t1").start();
		new Thread(()->t.m2(), "t2").start();*/

		new Thread(t::m1, "t1").start();
		new Thread(t::m2, "t2").start();

		/*
		//1.8之前的写法
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.m1();
			}

		});
		*/

	}

//	public synchronized void m1(){
//		try {
//			System.out.println(Thread.currentThread().getName()+" is sleep...");
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(Thread.currentThread().getName()+"this is a synchronized method!");
//	}
//
//	public void m2(){
//		System.out.println(Thread.currentThread().getName()+"this is a not synchronized method!");
//	}
//
////	public void m(){
////		m1();//同步方法
////		m2();//非同步方法
////	}
//
//	public static void main(String[] args) {
//		T t = new T();
//		new Thread(t::m1, "t1").start();
//		new Thread(t::m2, "t2").start();
//	}
	
}
