package dataStructure.queue;

/**
 * Created by golden on 2016/9/20 0020.
 */
public interface MyQueue<T> {

    int size();

    //入队列
    boolean offer(T data);

    //出队列
    T poll();

    //获取队头元素
    T peek();

}
