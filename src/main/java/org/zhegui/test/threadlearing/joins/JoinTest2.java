package org.zhegui.test.threadlearing.joins;

/**
 * 解释Join的作用
 *    Join阻塞，等待当前线程销毁
 * @author Zhegui
 *
 */
public class JoinTest2 {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				try {
					int secondValue = (int)(Math.random() * 1000);
					System.out.println(secondValue);
					Thread.sleep(secondValue);
				} catch (Exception e) {
					
				}
				System.out.println("end");
			}
		});
		
		thread.start();
		thread.join();
		System.out.println("我想当thread线程销毁后再执行，我做到了");
	}
}
