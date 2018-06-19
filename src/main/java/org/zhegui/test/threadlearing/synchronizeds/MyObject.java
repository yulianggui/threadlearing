package org.zhegui.test.threadlearing.synchronizeds;

public class MyObject {

	/**
	 * 加了synchronized互斥，多个线程对同一个MyObject对象的调用会互斥
	 */
	public synchronized void methodA(){
		
		try {
			System.out.println("begin methodA threadName="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end time = "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 没有加synchronized，多个线程对同一个MyObject可以不用再methodB和methodA互斥
	 */
	public void methodB(){
		
		try {
			System.out.println("begin methodB threadName="+Thread.currentThread().getName()+"begin time = "+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end time = "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加了synchronized互斥，多个线程对同一个MyObject对象的调用会互斥,
	 * 并且methodC和methodA会存在互斥，即多个线程对同一个MyObject对象的
	 * methodA和methodC访问都是互斥的，不能存在同时访问同一个MyObject对象的
	 * methodA和methodC
	 */
	public synchronized void methodC(){
		
		try {
			System.out.println("begin methodC threadName="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end time = "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
