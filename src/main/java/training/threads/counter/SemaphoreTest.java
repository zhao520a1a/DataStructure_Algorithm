package training.threads.counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore(信号量) - 管理公共资源工具类，常用于限流
 * 计数器
 * @Description 抢车位： 3个车位，6辆车
 * @author: Golden
 * @date: 2020/3/14
 */

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //只有3个车位

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); //得到资源
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(3); //使用中
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); //释放资源
                }

            }, "Thread" + i).start();
        }


    }

}
