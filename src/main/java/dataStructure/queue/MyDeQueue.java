package dataStructure.queue;

/**
 * Created by golden on 2016/9/20 0020.
 */
public class MyDeQueue<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        public Node (T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
         first = null;
         last = null;
         size = 0;
    }


    public boolean offerFirst(T data) {  //在头插入
        Node<T> f = first;   //代表原来的头元素
        Node<T> newNode = new Node(data);
        newNode.next = first;
        first = newNode;
        if (f == null)   //若是第一个元素
            last = newNode;
        else
            f.prev = newNode;
        size++;
        return true;
    }

    public boolean offerLast(T data) {  //在尾插入
        Node<T> l = last;     //代表原来的尾元素
        Node newNode = new Node(data);
        newNode.prev = last;
        last = newNode;
        if(l == null)   //若是第一个元素
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    public T pollFirst() {
        if(first == null) {
            return null;
        }
        else {
            Node<T> next = first.next;   //要删头部的下一元素；
            T data  = first.data;
            first.next = null; // 断一条联系
            first = next;    //换头
            if (next == null)  //说明已经空了
                last = null;
            else
                next.prev = null; // 断另一条联系
            size--;
            return data;
        }

    }

    public T pollLast() {
        if(last == null) {
            return null;
        } else {
            Node prev = last.prev;
            T data = last.data;
            last.prev = null;
            last = prev;
            if(prev == null)
                first = null;
            else
                last.next = null;
            size --;
            return data;
        }

    }

    public T peek() {
        return first == null ? null : first.data;
    }


}
