package org.zhegui.test.threadlearing.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTask02 {

	public static void main(String[] args) {
		// 这是一种编程思想
		class MyTimerTask extends TimerTask{
				
				@Override
				public void run() {
					System.out.println("do something");
					new Timer().schedule(new MyTimerTask(), 2000);
				}
				 
		}
		 
		new Timer().schedule(new MyTimerTask(), 2000);
	}

}
