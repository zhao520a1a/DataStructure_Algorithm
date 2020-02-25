package dataStructure.list.node;

/**
 * Created by golden on 2017/4/21 0021.
 */
public class DeListNode<T> {
    public T item;
    public DeListNode<T> prev;
    public DeListNode<T> next;

    public DeListNode(DeListNode<T> prev, T element, DeListNode<T> next) {
        this.prev = prev;
        this.item = element;
        this.next = next;
    }
}
