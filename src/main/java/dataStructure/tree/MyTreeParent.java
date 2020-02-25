package dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/9/24 0024.
 */

/*
*父节点表示法：用node数组来记录父节点的索引
* 体会：这种方式是感觉像一种1对1的形式；因此在进行求深度等操作时，只能一个一个寻路上去；
*/
public class MyTreeParent<T> {

    public static class Node<T> {
        private T value;
        private int parentIndex;

        public Node(T data, int parentIndex) {
            this.value = data;
            this.parentIndex = parentIndex;
        }
        public String toString() {
            return "父节点表示法[value=" + value + ", parent="+ parentIndex  + "]";
        }
    }

    private int TreeMaxSize = 100; //树节点数的最大值
    private int treeSize;  //记录树中的节点数；
    private Node<T>[] nodes;

    public MyTreeParent() {
    }
    public MyTreeParent(T data) { //构造时就创建了一个root节点；
        nodes = new Node[this.TreeMaxSize];
        nodes[0] = new Node<T>(data, -1);   //由于root节点无父节点，因此这里将root的parentIndex赋为-1；
        treeSize++;
    }
    public MyTreeParent(T data, int TreeMaxSize) {  // 以指定根节点、指定treeSize创建树
        this.TreeMaxSize = TreeMaxSize;
        this.nodes = new Node[this.TreeMaxSize];
        nodes[0] = new Node<T>(data, -1);
        treeSize++;
    }

    public Node<T> getRoot() {
        return nodes[0];
    }

    // 为指定节点添加父节点
    public void add(T data, Node parent) {
        if( treeSize  > TreeMaxSize ) {
            throw new RuntimeException("该树已满，无法添加新节点");
        } else {
            for (int i = 0; i < TreeMaxSize; i++) {
                if (nodes[i] == null) {
                    nodes[i] = new Node(data, this.getParentIndex(parent));
                    treeSize++;
                    return ;
                }
            }
        }

    }

    private int getParentIndex(Node parent) {
        for(int i=0; i<TreeMaxSize; i++){
            if(nodes[i] == parent) {
                return i;
            }
        }
        return -1;
    }

    public List<Node<T>> getChildren(Node parent) {
        List<Node<T>> kids = new ArrayList<Node<T>>();
        for(int i=0; i<TreeMaxSize && nodes[i]!=null; i++) {
            if ( nodes[i].parentIndex == this.getParentIndex(parent)) {
                kids.add(nodes[i]);
            }
        }
        return kids;
    }

    public int getDeep() {
        int maxDeep = 0;
        //对树中所有的有效节点进行遍历来比较层次，树中节点的最大层次值即为树的深度（高度）
        for(int i=0; i<TreeMaxSize && nodes[i] != null; i++) {
            int deep = 1;  //初始化本节点的深度
            int parentIndex = nodes[i].parentIndex; //当前节点的父节点的位置
            while(parentIndex != -1 && nodes[parentIndex] != null) {
                parentIndex =  nodes[parentIndex].parentIndex;  //向上继续搜索父节点
                deep++;
            }
            if(deep > maxDeep) {
                maxDeep = deep;
            }
        }
        return maxDeep;
    }


}
