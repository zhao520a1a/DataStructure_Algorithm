package dataStructure.stack;

/**
 * Created by golden on 2016/9/19 0019.
 */
public class MyLinkedStack<T> implements MyStack<T> {

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }

    private Node<T> top; //表示栈顶元素
    private int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public boolean push(T data) {
        Node<T> newNode = new Node<T>();
        newNode.data = data;
        newNode.next = top;    // 改变栈顶
        top = newNode;
        size++;
        return true;
    }

    @Override
    public T pop() {
        if(top ==null ) {
            return null;
        }
        Node<T> oldNode = top;
        top = top.next;    // 改变栈顶
        size --;
        return oldNode.data;
    }

    @Override
    public T peek() {
        if(top ==null ) {
            return null;
        }
        return top.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Node<T> i=top; i!=null; i=i.next) {
            sb.append(i.data.toString() + " ");
        }
        return sb.toString();
    }
}
