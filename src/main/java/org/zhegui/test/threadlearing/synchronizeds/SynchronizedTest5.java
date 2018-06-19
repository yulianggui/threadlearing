package org.zhegui.test.threadlearing.synchronizeds;

/**
	13. 死锁
	    a上了 a对象锁，在其中又上了 b对象锁。。。。
 * @author Zhegui
 *
 */
public class SynchronizedTest5 {
	public static void main(String[] args) throws Exception {
		SiSuoTest test = new SiSuoTest();
		
		Thread a = new Thread(test);
		a.setName("a");
		a.start();
		Thread b = new Thread(test);
		b.start();
	}
}


class SiSuoTest implements Runnable{
	private Object a = new Object();
	
	private Object b = new Object();
	
	
	public void run() {
		if("a".equals(Thread.currentThread().getName())){
			synchronized (a) {
				
				System.out.println("线程A  do something");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (b) {
					System.out.println("a --》线程B do something");
				}
				
			}
		}else{
			synchronized (b) {
				
				System.out.println("线程B  do something");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (a) {
					System.out.println("b --》线程A do something");
				}
				
			}
		}
		
	}
}


