package org.zhegui.test.threadlearing.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统计时器的使用
 *    Timer
 *    TimerTask
 * @author Zhegui
 *
 */
public class TraditionalTimerTest {
	
	 public static void main(String[] args) {
		 
		 // 这是一个阻塞方法
		 // 如果不传300，则只执行一次
		 // 延迟100，每个300后再次执行
		 new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				System.out.println("do something");
			}
			 
		 }, 100, 300);
		 
		 
	 }
}
