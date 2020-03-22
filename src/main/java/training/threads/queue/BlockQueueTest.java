package training.threads.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description  阻塞队列
 *  -  四组API:
 * | 当队列满时，处理方式 | 抛出异常 | 返回boolean值 | 一直阻塞 | 超时退出 |
 * | --- | --- | --- | --- | --- |
 * | 插入 | add | offer | put | offer(e，time,timeUnit) |
 * | 移除 | remove | poll | take | poll(e,time,timeUnit) |
 * | 检查对头 | element | peek | - | - |
 *
 * @author: Golden
 * @date: 2020/3/15
 */

public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
        //满了 抛异常
//        blockingQueue.add(1);
//        blockingQueue.add(2);
//        blockingQueue.add(3);
//        blockingQueue.add(4);
//        blockingQueue.remove();

        // 满了 返回boolean值
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        blockingQueue.offer(4);
        blockingQueue.forEach(p -> System.out.println(p));

//        blockingQueue.put(1);
//        blockingQueue.put(2);
//        blockingQueue.put(3);
//        blockingQueue.put(4);

//        System.out.println("检索并移除队头：" + blockingQueue.take());
//        System.out.println("检索队头：" + blockingQueue.element());


    }

}
