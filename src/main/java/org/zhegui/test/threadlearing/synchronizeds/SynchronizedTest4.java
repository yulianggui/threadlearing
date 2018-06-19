package org.zhegui.test.threadlearing.synchronizeds;

/**
 * 11.	当一个持有锁的线程出现异常是，该线程所持有的锁将会被释放。
 * 
 *   先让A线程开启，主线程休眠30毫秒，确保先启动threadA线程
 *       如后，A线程出现异常，会释放锁
 *       此时B线程即可获取锁
 * @author Zhegui
 *
 */
public class SynchronizedTest4 {
	public static void main(String[] args) throws Exception {
		ExceptionEntiy exceptionEntiy = new ExceptionEntiy();
		ThreadExceptionTest threadA = new ThreadExceptionTest(exceptionEntiy);
		threadA.setName("a");
		ThreadExceptionTest threadB = new ThreadExceptionTest(exceptionEntiy);
		threadB.setName("b");
		threadA.start();
		threadA.sleep(30);
		threadB.start();
	}
}

class ThreadExceptionTest extends Thread{
	
	private ExceptionEntiy exceptionEntiy;
	
	public ThreadExceptionTest(ExceptionEntiy exceptionEntiy){
		this.exceptionEntiy = exceptionEntiy;
	}
	
	@Override
	public void run() {
		this.exceptionEntiy.mytest();
	}
}

class ExceptionEntiy{
	public synchronized void mytest(){
		if(Thread.currentThread().getName().equals("a")){
			System.out.println(" aa threadName="+Thread.currentThread().getName());
			int i=0;
			while(true){
				i++;
				System.out.println("aaa i="+i);
				if(i>500000){
				
					i = 1/0;
				}
				
			}
		}else{
			System.out.println("bb threadName="+Thread.currentThread().getName());
		}
	}
	
}

