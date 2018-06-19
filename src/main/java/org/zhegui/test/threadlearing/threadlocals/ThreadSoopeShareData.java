package org.zhegui.test.threadlearing.threadlocals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadSoopeShareData {

	private static int data = 0;
	
	private static Map<Thread, Integer> dataMap = new HashMap<Thread, Integer>();
	
	public static void main(String[] args) {
		for(int i=0; i<2;i++){
			new Thread(new Runnable() {
				
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data : " + data);
					dataMap.put(Thread.currentThread(), data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public int get(){
			System.out.println("A from "+Thread.currentThread().getName() 
					+ " get data : "+dataMap.get(Thread.currentThread()));
			return data;
		}
	}
	
	static class B{
		public int get(){
			System.out.println("B from "+Thread.currentThread().getName() 
					+ " get data : "+dataMap.get(Thread.currentThread()));
			return data;
		}
	}

}

/**
 * 线程数据  单例
 * @author Zhegui
 *
 */
class ThreadScopeData{
	private ThreadScopeData(){};
	
	private static ThreadLocal<ThreadScopeData> map = new ThreadLocal<ThreadScopeData>();
	
	public static ThreadScopeData getThreadInstance(){
		ThreadScopeData instance = map.get();
		if(instance == null){
			instance = new ThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
}
