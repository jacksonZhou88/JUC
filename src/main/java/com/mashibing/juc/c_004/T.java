/**
 * synchronized�ؼ���
 * ��ĳ���������
 * @author mashibing
 */

package com.mashibing.juc.c_004;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //�����ͬ��synchronized(FineCoarseLock.class)
		mm();
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		synchronized(T.class) { //����һ������дsynchronized(this)�Ƿ���ԣ�
			count --;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}


	public static void main(String[] args) {
		new Thread(() -> T.m(), "t1").start();
	}
}
