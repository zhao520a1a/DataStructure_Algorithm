package training.threads.counter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description 加法计数器
 * @author: Golden
 * @date: 2020/3/14
 */

public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        //当计数器满足条件，就会执行后面的Runnable接口；否则会一直阻塞等待；
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("7颗龙珠集齐，可以召唤神龙！");
        });

        for (int i = 0; i < 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println("第" + temp + "颗龙珠集齐");
                try {
                    cyclicBarrier.await(); //阻塞等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }


    }

}
