package training.threads.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @Description 同步队列 - 每一个put操作，必须等待一个take，否则无法继续添加元素；
 * @author: Golden
 * @date: 2020/3/15
 */

public class SychronizedQueue {

    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        for (int i = 0; i < 5; i++) {
            int value = i;
            new Thread(() -> {
                try {
                    synchronousQueue.put(value);
                    System.out.println("插入：" + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println("取出：" + synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }
    }

}
