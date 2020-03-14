package training.threads.lock;

import training.threads.threadpool.ThreadUtils;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 买票问题
 * @author: Golden
 * @date: 2020/3/13
 */

public class SaleTicket {
    private int ticketNum = 30;



    // 使用synchronized
    private synchronized void saleTicket1()  {
        if (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + "the rest of ticketNum " + --ticketNum);
        }
    }


    //使用ReentrantLock
    private void saleTicket2() {
        //可重入锁
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();
        try {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "the rest of ticketNum " + --ticketNum);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }


    public static void main(String[] args)  {
        ThreadPoolExecutor threadPool = ThreadUtils.getThreadPool();
        SaleTicket saleTicket = new SaleTicket();
        System.out.println("wait After");
        threadPool.execute(() -> {
            for (int i = 0; i < 30; i++) {
                saleTicket.saleTicket1();
            }
        });
        threadPool.execute(() -> {
            for (int i = 0; i < 30; i++) {
                saleTicket.saleTicket1();
            }
        });
    }


}
