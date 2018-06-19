package org.zhegui.test.threadlearing.synchronizeds;

/**
 * 场景：
 *     子线程执行10次输出，再到主线程执行100次输出，接着道子线程执行10次，。。。
 *        如此循环50次
 *        
 *    要点：
 *       1： 线程互斥
 *       2： 线程通信    
 * @author Zhegui
 *
 */
public class SynchronizedTest02 {

	public static void main(String[] args) {
		
		new SynchronizedTest02().init();
		
	}
	
	private void init(){
		final Bussiness bussiness = new Bussiness();
		
		// 子线程
		new Thread(new Runnable() {
			
			public void run() {
				for(int i=0; i<50; i++){
					bussiness.sub(i);
				}
			}
		}).start();
		
		
		// 主线程
		for(int i=0; i<50; i++){
			bussiness.main(i);
		}
	}
	
	class Bussiness{
		
		private volatile boolean isSub = true;
		
		public synchronized void sub(int i){
			// 判断当前是否为子线程
			// 如果不是子线程，则等待
			if(!isSub){
				try {
					this.wait();  // 让出CPU
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j=0; j<10; j++){
				System.out.println("sub 第 j="+(j+1) +" 次打印" + "  i="+(i+1));
			}
			isSub = false;
			this.notify();  // 唤醒访问该 对象资源 的一个线程
		}
		
		public synchronized void main(int i){
			
			//这里，while比if还好
			// JDK源码wait里面建议这样用
			// 具体原因看JDK， 有时候会存在伪唤醒，不是主动唤醒
			while(isSub){
				try {
					this.wait(); // 当前线程等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j=0; j<10; j++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("main 第 j="+(j+1) +" 次打印" + "  i="+(i+1));
			}
			isSub = true;
			this.notify();  // 唤醒
		}
		
	}
	
}
