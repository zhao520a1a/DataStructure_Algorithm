package dataStructure.list;


import dataStructure.list.node.ListNode;

public class MyLinkedList<T> implements MyList<T> {


    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;


    private void checkElementIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 返回指定索引节点的前一个节点；
    ListNode<T> getPrevNodeByIndex(int index) {
        this.checkElementIndex(index);
        if (index == 0) {
            return null;
        } else {
            ListNode<T> n = head;
            for (int i = 0; i < index - 1; i++)
                n = n.next;
            return n;
        }
    }

    // 返回指定索引节点；  规定索引从0开始；
    ListNode<T> getNodeByIndex(int index) {
        this.checkElementIndex(index);
        ListNode<T> x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    @Override
    public T getValueByIndex(int index) {
        return this.getNodeByIndex(index) == null ? null : this.getNodeByIndex(index).item;
    }

    @Override
    //采用尾插法为链表添加新节点。
    public boolean add(T element) {
        ListNode t = tail;  //保存原来的尾元素
        ListNode newNode = new ListNode(element);
        tail = newNode;
        if (t == null)  //第一个元素
            head = newNode;
        else
            t.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        this.checkElementIndex(index);
        if (index == size) {  //在表尾添加节点；
            this.add(element);
        } else {             //在表中添加节点；\
            ListNode<T> prev = this.getPrevNodeByIndex(index);
            //注意：不能采取：ListNode<T> next = prev.next;因为prev可能为null；
            ListNode<T> next = this.getNodeByIndex(index);
            ListNode<T> newNode = new ListNode<T>(element);
            if (prev == null) {  //添加为表头；
                head = newNode;
            } else {
                prev.next = newNode;
            }
            newNode.next = next;
            size++;
        }
        return true;
    }

    @Override
    //由于只有一个next,即：只能找到下一个节点，因此删除操作：要从删除节点的上一个节点入手。
    public T remove(int index) {
        return remove(getPrevNodeByIndex(index));
    }

    public T remove(ListNode<T> prev) {
        T element;
        ListNode<T> next;
        if (prev == null) {  //删除的是头节点
            element = head.item;
            next = head.next;
            head.next = null;
            head = next;
        } else {
            element = prev.next.item;
            next = prev.next.next;
            if (next == null) {  //删除的是尾节点
                prev.next = null;
                tail = prev;
            } else {     //删除的是中间节点
                prev.next.next = null;
                prev.next = next;
            }
        }
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    public void clear() {
        for (ListNode<T> x = head; x != null; ) {   //很讨巧的方式
            ListNode<T> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (ListNode<T> x = head; x != null; x = x.next){
            result[i++] = x.item;
        }
        return result;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }
}
