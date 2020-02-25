package dataStructure.binaryTree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by golden on 2016/9/27 0027.
 * 采用递归的方式创建一颗二叉树
 * 传入的是二叉树的数组表示法:0表示该位置没有节点，从数组标号为0开始，用2n+1和2n+2分别表示左右两个孩子；
 * 采用二叉链表表示法构造二叉树
 */

public class GenerateBinaryTree {

    private TreeNode root;

    public GenerateBinaryTree(Integer[] array) {
        //root = makeBinaryTreeByArray(array, 0);  //返回根节点；注意：从数组下标为0开始构造;
       root = makeBinaryTreeByArray(array);  //返回根节点；注意：从数组下标为0开始构造;
    }

    public TreeNode getRoot() {
        return this.root;
    }


    public TreeNode makeBinaryTreeByArray(Integer[] array, int index) {
        if (index < array.length) {
            if (array[index] != null) {
                TreeNode t = new TreeNode(array[index]);
                array[index] = null;
                t.left = makeBinaryTreeByArray(array, index * 2 + 1);
                t.right = makeBinaryTreeByArray(array, index * 2 + 2);
                return t;
            }
        }
        return null;
    }


    /**
     * 采用非递归的方式创建一颗二叉树
     * 传入的是二叉树的数组表示法:0表示该位置没有节点，从数组标号为0开始，用2n+1和2n+2分别表示左右两个孩子；
     * 构造后是二叉树的二叉链表表示法
     */
    public TreeNode makeBinaryTreeByArray(Integer[] items) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>() ;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (root == null) {
                    root = new TreeNode(items[i]);
                    treeNodes.add(root);
                }
                TreeNode current =  treeNodes.remove(0);  //相当于队列的删除队头，并返回删除的节点； 通过这种方式，让各节点建立起关联，最终形成二叉树；
                if (i < items.length / 2) {  //防止找左右孩子的时候越界
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    if (items[left] != null) {
                        current.left = new TreeNode(items[left]);
                        treeNodes.add( current.left);
                    }
                    if (right < items.length && items[right] != null) {
                        current.right = new TreeNode(items[right]);
                        treeNodes.add( current.right);

                    }
                }

            }
        }
        return  root;
    }

}
