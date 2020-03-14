package training.threads.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过一个精确通知顺序访问的案例加深对Condition的使用。
 * 通过给每个线程不同的condition,然后用condition.signal()精确地通知对应的线程继续执行。
 *
 * @Description 实现多线程间精确通知访问顺序 （线程A -> 线程B -> 线程C），按顺序调用，实现AA->BB->CC依次打印。
 * @author: Golden
 * @date: 2020/3/13
 */

public class AccurateAccess {
    private static Lock lock = new ReentrantLock();
    private static Condition[] conditions = {lock.newCondition(), lock.newCondition(), lock.newCondition()};

    private volatile int flag = 1; //标识位 1-线程A 2-线程B 3-线程C

    private void printf(int flag, String msg) {
        lock.lock();
        try {
            while (this.flag != flag) {
                conditions[flag - 1].await();
            }

            System.out.println(Thread.currentThread().getName() + " flag:" + flag + " msg:" + msg);
            this.flag = flag % 3 + 1;

            conditions[this.flag - 1].signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        AccurateAccess access = new AccurateAccess();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                access.printf(1, "AAA");
            }
        }, "Thread-A").start();


        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                access.printf(2, "BBB");
            }
        }, "Thread-B").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                access.printf(3, "CCC");
            }
        }, "Thread-C").start();

    }

}
