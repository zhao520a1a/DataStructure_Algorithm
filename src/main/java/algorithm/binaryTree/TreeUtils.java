package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


public class TreeUtils {


    private static TreeNode root;


    public TreeNode getRoot() {
        return root;
    }


    /**
     * 采用非递归的方式创建一颗二叉树
     * 传入的是二叉树的数组表示法:0表示该位置没有节点，从数组标号为0开始，用2n+1和2n+2分别表示左右两个孩子；
     * 构造后是二叉树的二叉链表表示法
     */
    public static <T> TreeNode makeBinaryTreeByArray(T[] items) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (root == null) {
                    root = new TreeNode<>(items[i]);
                    nodes.add(root);
                }
                TreeNode current = nodes.remove(0);  //相当于队列的删除队头，并返回删除的节点； 通过这种方式，让各节点建立起关联，最终形成二叉树；
                if (i < items.length / 2) {  //防止找左右孩子的时候越界
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    if (items[left] != null) {
                        current.left = new TreeNode<>(items[left]);
                        nodes.add(current.left);
                    }
                    if (right < items.length && items[right] != null) {
                        current.right = new TreeNode<>(items[right]);
                        nodes.add(current.right);
                    }
                }
            }
        }
        return root;
    }


    /**
     * 采用递归的方式创建一颗二叉树
     * 传入的是二叉树的数组表示法:0表示该位置没有节点，从数组标号为0开始，用2n+1和2n+2分别表示左右两个孩子；
     * 构造后是二叉树的二叉链表表示法
     */
    public TreeNode<Integer> makeBinaryTreeByArray(Integer[] array, int index) {
        if (index < array.length) {
            if (array[index] != null) {
                TreeNode<Integer> t = new TreeNode<>(array[index]);
                array[index] = null;
                t.left = makeBinaryTreeByArray(array, index * 2 + 1);
                t.right = makeBinaryTreeByArray(array, index * 2 + 2);
                return t;
            }
        }
        return null;
    }


    /**
     * 采用递归的方式创建一颗二叉树
     * n 行每行三个整数 fa，lch，rch，
     * 表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
     * 构造后是二叉树的二叉链表表示法
     */
    //创建一个树的结点
    public static TreeNode<String> makeBinaryTreeByInput(BufferedReader in) throws Exception {
        String[] nodeVals = in.readLine().split(" ");
        //数组中第一个数是根节点
        TreeNode<String> node = new TreeNode<>(nodeVals[0]);
        //通过递归确定了层数
        if (!"0".equals(nodeVals[1])) {
            node.left = makeBinaryTreeByInput(in);
        }
        if (!"0".equals(nodeVals[2])) {
            node.right = makeBinaryTreeByInput(in);
        }
        return node;
    }


}
