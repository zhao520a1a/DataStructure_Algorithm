package dataStructure.queue;

import java.util.Arrays;

/**:
 * @Description 用数组实现队列操作
 * 有趣的现象： JDK中ArrayQueue中的容量是0开始计数的；
 * @author: Golden
 * @data: 2019/10/12
 */

public class ArrayQueue<T> implements MyQueue<T> {
    private T[] items;
    private int capacity;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
        items = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public int size() {
        return tail - head;
    }

    public void resize(int size) {
        if (size < this.size()) {
            throw new IndexOutOfBoundsException("Resizing would lose data, 会丢失数据");
        }
        if (size != capacity) {
            T[] newItmes = (T[]) new Object[size];
            System.arraycopy(items, 0, newItmes, 0, this.size());
            items = newItmes;
            capacity = size;
        }
    }

    public void clear() {
        head = tail = 0;
        Arrays.fill(items, null);
    }

    @Override
    public boolean offer(T data) {
        if (tail == capacity) {
            return false;
        }
        items[tail++] = data;
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return items[tail--];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return items[tail];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = head; i < tail; i++) {
            sb.append(items[i].toString() + " ");
        }
        return sb.toString();
    }


}
