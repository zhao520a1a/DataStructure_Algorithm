package dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/9/24 0024.
* 孩子（子节点链）表示法
* 体会：这种方式是感觉像一种1对M的形式｛一个父节点后关联着多个子节点｝；因此在进行求深度等操作时，可以用n = n.next，方便操作；
*/
public class MyTreeChild<E> {

    //树中的一个节点
    public static class Node<E> {
        private E value;
        private ChildIndexNode<E> first; // 用来连接第一个子节点

        public Node(E value) {
            this.value = value;
        }

        public String toString() {
            String f = first == null ? null :  Integer.toString( first.getChildIndex() );
            return "父节点表示法[value=" + value + ", first=" + f + "]";
        }
    }

    //一个节点的单链表，用来存储该节点的孩子的索引信息；
    public static class ChildIndexNode<T> {
        private int childIndex;  // 记录子节点的索引号
        private ChildIndexNode<T> next;

        public ChildIndexNode(int childIndex, ChildIndexNode<T> next) {
            this.childIndex = childIndex;
            this.next = next;
        }
        public int getChildIndex() {
            return childIndex;
        }
    }

    private int treeMaxSize = 100;  //树节点数的最大值
    private int treeSize = 0;   //记录树中的节点数；
    private Node<E>[] nodes;  // 使用一个Node[]数组来记录该树的所有节点

    // 以指定根节点创建树
    public MyTreeChild() {
    }

    // 以指定根节点创建树
    public MyTreeChild(E data) {
        nodes = new Node[this.treeMaxSize];
        nodes[0] = new Node<E>(data);
    }

    // 以指定根节点、指定treeSize创建树
    public MyTreeChild(E data, int treeMaxSize) {
        this.treeMaxSize = treeMaxSize;
        this.nodes = new Node[this.treeMaxSize];
        nodes[0] = new Node<E>(data);
        treeSize++;
    }


    // 为指定节点添加子节点
    public void add(E data, Node<E> parent) {
        for (int i = 0; i < treeMaxSize; i++) {
            if (nodes[i] == null) {
                nodes[i] = new Node<E>(data);
                if (parent.first == null) {
                    // 若该节点没有第一个子节点，那么就新建子节点链
                    parent.first = new ChildIndexNode<E>(i, null);
                } else {
                    // 若有，则依次取该节点的子节点，直到叶子节点
                    ChildIndexNode c = parent.first;
                    while (c.next != null) {
                        c = c.next;
                    }
                    c.next = new ChildIndexNode(i, null);
                }
                treeSize++;
                return;
            }
        }
        throw new RuntimeException("该树已满，无法添加新节点");
    }

    // 判断是否为空
    public boolean isEmpty() {
        return nodes[0] == null; // root节点是否为空
    }

    // 获取根节点
    public Node<E> getRoot() {
        return nodes[0];
    }

    // 获取指定节点的所有子节点
    public List<Node<E>> getChildren(Node<E> parent) {
        List<Node<E>> childs = new ArrayList<Node<E>>();
        for (ChildIndexNode n = parent.first; n != null; n = n.next) {
            Node<E> child = nodes[n.childIndex];
            childs.add(child);
        }
        return childs;
    }

    // 返回指定节点的第index个子节点，注：index是从1开始；
    public Node<E> getChildByIndex(Node<E> parent, int index) {
        ChildIndexNode c = parent.first;
        for(int i=1; c != null; i++) {  // 沿着孩子链一直搜寻
            if(index == i) {
                return nodes[c.childIndex];
            }
        }
        return null;
    }

    // 树的深度=取所有子节点的最大层次
    public int getDeep() {
        return this.getDeep(this.getRoot());
    }

    // 通过递归的方式返回某个节点的层次(取其所有子节点最大层次+1)
    public int getDeep(Node<E> node) {
        if (node.first == null) {  //只有一个root节点；
            return 1;
        } else {
            int maxDeep = 0;
            ChildIndexNode n = node.first;
            while (n != null) {
                // 获取以其子节点为根的子树的深度
                Node<E> child = nodes[n.childIndex];
                int deep = this.getDeep(child);  //运用递归，自上而下的得到各个分支子节点的深度；
                if (deep > maxDeep) {
                    maxDeep = deep;
                }
                n = n.next;
            }
            return maxDeep + 1;
        }
    }

    // 返回包含指定节点的索引;注意：树中的节点是连续存储的； 注：节点索引编号是从0开始的；
    public int getIndex(Node<E> node) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

}
