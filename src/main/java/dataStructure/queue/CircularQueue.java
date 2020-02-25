package dataStructure.queue;

import java.util.Arrays;

/**
 * @Description 循环队列
 * 技巧： (tail + 1)% capacity  == head 判是否首尾相连
 *
 * @author: Golden
 * @date: 2019/10/12
 */

public class CircularQueue<T> implements MyQueue<T> {

    private T[] items;
    private int head;
    private int tail;
    private int capacity;

    public  CircularQueue(int capacity) {
        head = 0;
        tail = 0;
        this.capacity = capacity;
        items = (T[]) new Object[capacity];
    }


    public boolean isEmpty() {
        return tail == head;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    @Override
    public int size() {
        int size =  tail - head;
        if(size < 0) {
            size += capacity;
        }
        return size;
    }


    public void clear() {
        head = tail = 0;
        Arrays.fill(items, null);
    }

    @Override
    public boolean offer(T data) {
        if(isFull()) {
            return false;
        }
        items[tail] = data;
        tail = (tail + 1) % capacity;
        return false;
    }

    @Override
    public T poll() {
        if(isEmpty()) {
            return null;
        }
        T item = items[head];
        head = (head + 1) % capacity;
        return item;
    }

    @Override
    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return items[head];
    }
}
