package org.zhegui.test.threadlearing.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存设计，读写锁
 * @author Zhegui
 *
 */
public class CacheTest {
	
	private Map<String, Object> cache = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		
	}
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	
	// 要理解这个...
	public Object getData(String key){
		
		// 上读锁
		rwl.readLock().lock();
		Object value = null;
		try {
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if(value == null){
						value = "aaaaa";   // 模拟查询db
					}
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally {
			rwl.readLock().unlock();
		}
		return value;
	}
}
