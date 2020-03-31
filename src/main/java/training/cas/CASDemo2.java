package training.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description 原子引用解决CAS的ABA为问题， 通过版本号来实现；原理类似于乐观锁！
 * @author: Golden
 * @date: 2020/3/22
 */

public class CASDemo2 {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            //1. 获得版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("T1 stamp 01=>" + stamp);

            //2. 版本号加一
            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("T1 stamp 02=>" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("T1 stamp 03=>" + atomicStampedReference.getStamp());
        }, "T1").start();

        new Thread(() -> {
            //1, 获得版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("T1 stamp 01=>" + stamp);
            // 保证上面的线程先执行完毕！
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicStampedReference.compareAndSet(100, 2019,
                    stamp, stamp + 1);
            System.out.println("T2 是否修改成功：" + b);
            System.out.println("T2 最新的stamp：" + stamp);
            System.out.println("T2 当前的最新值：" + atomicStampedReference.getReference());
        }, "T2").start();

    }


}
