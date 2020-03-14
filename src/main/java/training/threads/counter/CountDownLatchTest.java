package training.threads.counter;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 减法计数器
 * @author: Golden
 * @date: 2020/3/14
 */

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Start;");
                downLatch.countDown();  //计数器减一
            }, "Thread" + i).start();
        }
        downLatch.await(); //阻塞等待计数器归零

        System.out.println("所有线程执行完毕");

    }

}
