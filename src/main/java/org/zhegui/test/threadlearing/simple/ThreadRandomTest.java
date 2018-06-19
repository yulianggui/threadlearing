package org.zhegui.test.threadlearing.simple;

import java.util.Random;

/**
 * 演示线程的随机性
 *    CPU执行的随机性
 *       异步执行
 *       
 * @author Zhegui
 *
 */
public class ThreadRandomTest {

	public static void main(String[] args) {
		MyTestRandomThread aa = new MyTestRandomThread();
		aa.start();
		
		for(int i=0; i<30; i++){
			int time = new Random().nextInt(3000);
			try {
				Thread.sleep(time);
				System.out.println("main="+Thread.currentThread().getName() + "i= "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


class MyTestRandomThread extends Thread{
	
	public void run() {
		for(int i=0; i<30; i++){
			int time = new Random().nextInt(3000);
			try {
				Thread.sleep(time);
				System.out.println(Thread.currentThread().getName() + "i= "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}