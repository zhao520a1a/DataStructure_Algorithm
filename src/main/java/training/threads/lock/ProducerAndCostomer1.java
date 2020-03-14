package training.threads.lock;

import training.threads.threadpool.ThreadUtils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 生产者消费者问题 : 判读 干活 通知
 * 1. 防止虚假唤醒,判读时要在while循环中wait();
 *
 * @author: Golden
 * @date: 2020/3/13
 */

public class ProducerAndCostomer1 {

    private int num = 0;

    private synchronized void increment() throws InterruptedException {
        //判断 : 这里要用While,不可以用if,防止虚假唤醒；
        while(num != 0) {
            this.wait();
        }
        //干活
        num ++;
        System.out.println(Thread.currentThread().getName() + " producer Num:" + num);
        //通知
        this.notifyAll();
    }

    private synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num --;
        System.out.println(Thread.currentThread().getName() + " costomer Num:" + num);
        this.notifyAll();
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = ThreadUtils.getThreadPool();
        ProducerAndCostomer1 pc = new ProducerAndCostomer1();

        threadPool.execute(()->{
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


        threadPool.execute(()->{
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
