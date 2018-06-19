package org.zhegui.test.threadlearing.yields;
/**
 * Yield方法的作用是放弃当前的cpu时间，将它让给其他的任务去占用CPU执行时间。
 * 但放弃的时间不确定，有可能刚刚放弃，马上又获得CPU时间片。
 * 
 *   分别运行注释掉Thread.yield();
 *   和未注释掉的代码对比耗时
 * @author Zhegui
 *
 */
public class YieldTest {
	public static void main(String[] args) {
		new ThreadYield().start();
		
	}
}

class ThreadYield extends Thread{
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		int count = 0;
		for(int i=0; i<50000000; i++){
			Thread.yield();
			count = count + i;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("共耗时："+(endTime-startTime)+"毫秒");
	}
} 
