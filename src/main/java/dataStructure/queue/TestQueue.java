package dataStructure.queue;

/**
 * @Description 测试队列
 * @author: Golden
 * @date: 2019/10/13
 */

public class TestQueue {

    public static void main(String[] args) throws Exception {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(20);

        arrayQueue.size();
        for(int i=0; i<10; i++) {
            arrayQueue.offer(i);
        }
        System.out.println(arrayQueue.toString());


    }
}
