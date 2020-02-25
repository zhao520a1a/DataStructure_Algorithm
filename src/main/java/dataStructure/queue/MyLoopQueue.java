package dataStructure.queue;

import java.util.Arrays;

/**
 * 循环队列
 * Created by golden on 2016/9/20 0020.
 */
public class MyLoopQueue<T> implements MyQueue {

    private Object[] objs = new Object[15];
    private int front = 0;
    private int rear = 0;

    public boolean isEmpty() {
        return rear == front && objs[rear] == null;//头跟尾相交,元素为null；表“空”
    }

    @Override
    public int size() {
        return (rear - front + objs.length) % objs.length;
    }

    public void clear() {
        Arrays.fill(objs, null);
        front = rear = 0;
    }

    @Override
    public boolean offer(Object data) {
        if (rear == front && objs[rear] != null) {  //头尾相连，元素不为null; 表“满”
            reSize();
        }
        objs[rear++ % objs.length] = data;  //如果rear已经到头，那就转头，是个循环过程
        return false;
    }

    private void reSize() {
        Object[] temp = new Object[objs.length * 3 / 2 + 1];
        for (int i = 0; i < objs.length; i++) {
            temp[i] = objs[i];
            objs[i] = null;
        }
        objs = temp;
    }

    @Override
    public  T poll() {
        if (rear == front && objs[rear] == null) {
            return null;
        }
        T oldValue = (T) objs[front];
        objs[front++ % objs.length] = null;
        return oldValue;
    }

    @Override
    public T peek() {
        if (rear == front && objs[rear] == null) {
            return null;
        }
        return  (T)objs[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (rear > front) {
            for (int i = front; i < rear; i++) {
                sb.append(objs[i].toString() + " ");
            }
        }
        else {  //要分段进行遍历
            for (int i = front; i < objs.length; i++) {
                sb.append(objs[i].toString() + " ");
            }
            for(int i = 0; i< rear; i++) {
                sb.append(objs[i].toString() + " ");
            }
        }
        return sb.toString();
    }
}
