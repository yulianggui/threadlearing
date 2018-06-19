package org.zhegui.test.threadlearing.joins;

public class JoinTest01 {
	public static void main(String[] args) {
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
		///Thread.sleep(???);
		System.out.println("我想当thread线程销毁后再执行");
		System.out.println("想太多了吧！，不存在的！");
	}
}
