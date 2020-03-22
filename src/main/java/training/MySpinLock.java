package training;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description 自定义自旋锁
 * @author: Golden
 * @date: 2020/3/22
 */

public class MySpinLock {


    // 原子引用 CAS
    AtomicReference<Thread> atomicReference = new AtomicReference<>();


    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==mylock");
        // 期望是空的没有加锁， thread         // 自旋，（循环！）
        while (atomicReference.compareAndSet(null, thread)) {// cas

        }

    }

    // 解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "==myUnlock");
    }


}
