package dataStructure.list;

import dataStructure.list.node.DeListNode;

import java.util.NoSuchElementException;

/**
 * Created by golden on 2016/9/22 0022.
 */
public class MyDeList<T> {


    private DeListNode<T> first;
    private DeListNode<T> last;
    private int size;

    private void checkElementIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //由于是双链表，可采用折半的方法来优化查找
    DeListNode<T> getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < (size >> 1)) {  //右移1相当于除以2，即折半；
            DeListNode<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            DeListNode<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public boolean addFirst(T element) {
        DeListNode f = first;
        DeListNode newNode = new DeListNode(null, element, f);  //注意加入的f节点，不是first节点；
        first = newNode;
        //要考虑原来链表中是否为空的情况；
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        return true;
    }

    public boolean addLast(T element) {
        DeListNode l = last;
        DeListNode newNode = new DeListNode(l, element, null);  //注意加入的l节点，不是last节点；
        last = newNode;
        //要考虑原来链表中是否为空的情况；
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;
        return true;
    }

    public boolean add(int index, T element) {
        this.checkElementIndex(index);
        if (index == size)
            addLast(element);
        else {
            DeListNode<T> oldNode = this.getNodeByIndex(index);
            DeListNode<T> prev = oldNode.prev;
            DeListNode<T> newNode = new DeListNode<T>(prev, element, oldNode);
            oldNode.prev = newNode;
            if (prev == null)
                first = newNode;
            else
                prev.next = newNode;
            size++;
        }
        return true;
    }

    public T removeFirst() {
        //要考虑列表本身是否为空的情况；
        DeListNode<T> f = first;
        if (f == null)
            throw new NoSuchElementException();

        T element = f.item;
        DeListNode<T> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        //要考虑删除后列表是否为空的情况；
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public T removeLast() {
        //要考虑列表本身是否为空的情况；
        DeListNode<T> l = last;
        if (last == null)
            throw new NoSuchElementException();

        T element = last.item;
        DeListNode prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        //要考虑删除后列表是否为空的情况；
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    public T remove(int index) {
        this.checkElementIndex(index);
        return this.remove(this.getNodeByIndex(index));
    }

    //由于只有一个prev,next,因此删除操作：从删除节点入手即可。
    T remove(DeListNode<T> n) {
        T element = n.item;
        DeListNode<T> prev = n.prev;
        DeListNode<T> next = n.next;
        if (prev == null) {   //删除的是头节点
            first = next;
        } else {
            prev.next = next;
            n.prev = null;
        }
        if (next == null) {    //删除的是尾节点
            last = prev;
        } else {
            next.prev = prev;
            n.next = null;
        }
        n.item = null;
        size--;
        return element;
    }

    public T getValueByIndex(int index) {
        return this.getNodeByIndex(index) == null ? null : this.getNodeByIndex(index).item;
    }

    public int size() {
        return size;
    }

    void linkLast(T e) {
        final DeListNode<T> l = last;
        final DeListNode<T> newNode = new DeListNode(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }


    public void clear() {
        for (DeListNode<T> x = first; x != null; ) {   //很讨巧的方式
            DeListNode<T> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (DeListNode<T> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    public DeListNode<T> getFirst() {
        return first;
    }

    public DeListNode<T> getLast() {
        return last;
    }

}
