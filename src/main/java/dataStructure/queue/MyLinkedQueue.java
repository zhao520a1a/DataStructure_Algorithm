package dataStructure.queue;

/**
 * Created by golden on 2016/9/20 0020.
 */
public class MyLinkedQueue<T> implements MyQueue<T> {

    private static class Node<T> {
      private   T data;
      private   Node<T> next;
    }

    private int size;
    private Node<T> front;
    private Node<T> rear;

    public boolean isEmpty() {
        return  size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        front = null;
        rear = null;
    }

    @Override
    public boolean offer(T data) {
        if(front ==  null) {  //如果是空链队列
            front = new Node();
            front.data = data;
            rear = front;
        }
        else {  //非空队列
            Node<T> newNode = new Node<T>();
            newNode.data = data;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T poll() {
        Node<T> oldNode = front;
        front = front.next;
        front.next = null;
        size--;
        return oldNode.data;
    }

    @Override
    public T peek() {
        return front.data;
    }

    @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
         for(Node<T> i=front; i!=null ; i=i.next) {
            sb.append(i.data.toString() + " ");
         }
         return sb.toString();
    }
}
