package training.threads.lock;

import training.threads.threadpool.ThreadUtils;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 生产者消费者问题 : 判读 干活 通知
 * 1. 防止虚假唤醒,判读时要在while循环中wait();
 * @author: Golden
 * @date: 2020/3/13
 */

public class ProducerAndCostomer2 {

    private int num = 0;

    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断 : 这里要用While,不可以用if,防止虚假唤醒；
            while (num != 0) {
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + " producer Num:" + num);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " costomer Num:" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = ThreadUtils.getThreadPool();
        ProducerAndCostomer2 pc = new ProducerAndCostomer2();

        threadPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        threadPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
