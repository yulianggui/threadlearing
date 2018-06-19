package org.zhegui.test.threadlearing.pc;

/**
 * 测试生产者和消费者
 *     这里只有一个生产者和一个消费者的情况
 * @author Zhegui
 *
 */
public class PCTest01 {
	public static void main(String[] args) {
		String lock = "lock";
		PTest01 p = new PTest01(lock);
		CTest01 c = new CTest01(lock);
		ThreadP tp = new ThreadP(p);
		tp.start();
		ThreadC tc = new ThreadC(c);
		tc.start();
	}
}

/**
 * 模拟线程，生产者线程
 * @author Zhegui
 *
 */
class ThreadP extends Thread{
	
	PTest01 p;
	
	public ThreadP(PTest01 p){
		this.p = p;
	}
	
	@Override
	public void run() {
		while(true){
			p.setValue();
		}
	}
	
}

/**
 * 模拟线程，消费者线程
 * @author Zhegui
 *
 */
class ThreadC extends Thread{
	CTest01 c;
	
	public ThreadC(CTest01 c){
		this.c = c;
	}
	
	@Override
	public void run() {
		while(true){
			c.getValue();
		}
	}
}


/**
 * 模拟共享数据
 * @author Zhegui
 *
 */
class ValueTest01{
	
	public static String value="";
	
}

/**
 * 生产者职责类
 * @author Zhegui
 *
 */
class PTest01{
	
	private String lock;
	
	public PTest01(String lock){
		this.lock = lock;
	}
	
	/**
	 * 设置value值
	 */
	public void setValue(){
		try {
			synchronized(lock){
				
					/**
					 * 如果不为 ""，则等待被消费
					 */
					while(!ValueTest01.value.equals("")){
							lock.wait();
					}
					String value = System.currentTimeMillis() + "_"+System.nanoTime();
					System.out.println("set 的值是"+ value);
					ValueTest01.value = value;
					lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


/**
 * 消费者职责类
 * @author Zhegui
 *
 */
class CTest01{
	private String lock;
	
	public CTest01(String lock){
		this.lock = lock;
	}
	
	public void getValue(){
		try {
			synchronized (lock) {
				
				//当前生产者为空，则等待被唤醒
				while(ValueTest01.value.equals("")){
					lock.wait();
				}
				System.out.println("get的值是"+ValueTest01.value);
				ValueTest01.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
