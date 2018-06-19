package org.zhegui.test.threadlearing.conditionss;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest01 {

	
	public static void main(String[] args) throws Exception {
		final ConditionTest01 condition = new ConditionTest01();
		Thread threada = new Thread(new Runnable() {
			
			public void run() {
				condition.awaita();
			}
		});
		Thread threadB = new Thread(new Runnable() {
			
			public void run() {
				try {
					//Thread.sleep(5000);
					condition.signala();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		Thread threada1 = new Thread(new Runnable() {
			
			public void run() {
				condition.awaita();
			}
		});
		Thread threadB1 = new Thread(new Runnable() {
			
			public void run() {
				try {
					//Thread.sleep(5000);
					condition.signala();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		threada.setName("a");
		threadB.setName("B");
		threada1.setName("a1");
		threadB1.setName("B1");
		
		
		threada.start();
		threada1.start();
		
		Thread.sleep(300);
		threadB.start();
		threadB1.start();
	}
	
	/**
	 * 锁
	 */
	private Lock lock = new ReentrantLock();
	
	/**
	 * 获取一个条件
	 */
	private Condition condition = lock.newCondition();
	
	public void awaita(){
		try {
			lock.lock();
			System.out.println("将要进行await操作，当前线程进入等待状态，释放锁!，name="+Thread.currentThread().getName());
			// 使用条件锁
			condition.await();
			System.out.println("重新获取锁，name="+Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signala(){
		try {
			lock.lock();
			System.out.println("将要进行signal操作，唤醒等待的线程，name="+Thread.currentThread().getName());
			// 使用条件锁
			condition.signal();
			System.out.println("已经唤醒，name="+Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}
