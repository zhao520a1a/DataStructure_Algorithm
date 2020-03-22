package training;

import java.util.concurrent.TimeUnit;

/**
 * @Description 死锁案例
 * @author: Golden
 * @date: 2020/3/22
 */

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"T1").start();
        new Thread(new HoldLockThread(lockB,lockA),"T2").start();
    }
}


class HoldLockThread implements  Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        // A 想要拿B
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"lock:"+lockA+"=>get" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // B想要拿到A
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"lock:"+lockB+"=>get" + lockA);
            }
        }
    }
}