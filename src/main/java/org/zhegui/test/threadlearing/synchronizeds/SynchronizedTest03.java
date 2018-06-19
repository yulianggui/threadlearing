package org.zhegui.test.threadlearing.synchronizeds;

/**
 * 锁重入的概念
 * 
 *   锁重入的大概意思是，在synchronized方法/块中仍然可以调用synchronized修饰的方法，并且是永远可以得到锁的，
 * @author Zhegui
 *
 */
public class SynchronizedTest03 {
	public static void main(String[] args) {
		//1. 测试锁重入，在synchronized方法/块中仍然可以调用synchronized修饰的方法
		new Main().methodA();
		
		//2.测试锁重入的继承
		new Sub().subMethodA();
	}
}

class Main{
	
	protected int i=10;
	
	/**
	 * synchronized 方法1
	 */
	public synchronized void methodA(){
		System.out.println("抵达main methodA 方法！");
		this.methoddB();
	}
	
	/**
	 * synchronized 方法2
	 */
	public synchronized void methoddB(){
		System.out.println("抵达main methodB 方法！");
	}
	
	
	/**
	 * synchronized 方法2
	 */
	public synchronized void methoddC(){
		System.out.println("抵达main methodC 方法！");
		try{
			i--;
			System.out.println("i="+i);
			Thread.sleep(100);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Sub extends Main{
	
	public synchronized void subMethodA(){
		while(i>0){
			
			try {
				i--;
				System.out.println("i="+i);
				Thread.sleep(100);
				this.methoddC();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
