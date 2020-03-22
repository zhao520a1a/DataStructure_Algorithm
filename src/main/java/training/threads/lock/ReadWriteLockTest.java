package training.threads.lock;

import training.threads.threadpool.ThreadUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读写锁： 读写分离，提高下效率；只保证写操作的原子性；
 * @author: Golden
 * @date: 2020/3/15
 */

public class ReadWriteLockTest {


    public static void main(String[] args) {
        MyCache cache = new MyCache();

        //写线程
        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread( ()->{
                cache.put("name"+temp, temp+"");
            },"Thread" + i).start();
        }

        //读线程
        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread( ()->{
                cache.get("name"+temp);
            },"Thread" + i).start();
        }



    }


}

class MyCache {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private volatile Map<String, String> map = new HashMap<>();


    public void put(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String get(String key) {
        String value = "";
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成 ：key: " + key + " value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
       return value;
    }


}
