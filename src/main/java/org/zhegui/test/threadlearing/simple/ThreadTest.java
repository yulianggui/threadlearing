package org.zhegui.test.threadlearing.simple;

/**
 * 简单demo
 *    实现线程的两种方式
 *      1.写Thread子类（继承，重写 run）
 *      2.在Thread的构造参数传入 Runnable 
 * @author Zhegui
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		
	    //1. 
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(800);
						System.out.println("11111   current thread name = "+ Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		thread.start();
		
		// 2.第二种
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
						System.out.println("22222 current thread name = "+ Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		thread2.start();
		
		
		// 将会执行       “执行 run 方法1”
		// 原因： new Thread当前对象使用自己的run方法，如果没有则才去子类找
		//@Override
	    //public void run() {
	    //    if (target != null) {
	    //        target.run();
	    //    }
	    //}
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println("执行run 方法2");
			}
		}){
			public void run() {
				System.out.println("执行 run 方法1");
			};
		}.start();
	}

}
