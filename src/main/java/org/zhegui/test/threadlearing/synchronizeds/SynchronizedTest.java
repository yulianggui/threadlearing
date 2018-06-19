package org.zhegui.test.threadlearing.synchronizeds;

public class SynchronizedTest {
	
	
	public static void main(String[] args) {
		new SynchronizedTest().init();
		
	}
	
	
	
	
	
	// 这里会出现，output.output("abcdefghijk")还没打完就会别另一个线程抢用cup
	// 但不会出现竞争相同的资源
	private void init(){
		final Outputer output = new Outputer();
		new Thread(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
						output.output("abcdefghijk");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
						output.output("12345678965");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	class Outputer{
		public void output(String name){
			int len = name.length();
			for(int i=0; i<len; i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}

}
