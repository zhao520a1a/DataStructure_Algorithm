package algorithm.binaryTree;


import java.util.Deque;
import java.util.LinkedList;

import dataStructure.binaryTree.TreeNode;


/**
 * 按Z字形按层打印二叉树的节点
 * 思路：只使用一个双端队列dq
 * 定义规则：
 * 规则1：若顺序是从左到右，从dq的头部弹出节点node；若node有孩子，以“先左后右”的形式从尾部进入dq.
 * 规则2：若顺序是从右到左，从dq的尾部弹出节点node；若node有孩子，以“先右后左”的形式从头部进入dq.
 * 确定规则1和规则2转换时机：（实质是一个确定每一层最后一个节点的问题）
 * 经总结发现：当前层有孩子节点中最先进入dq的节点  ==  下一层最后打印的节点
 *
 * Created by golden on 204.
 */
public class PrintByZigZag {

    public  static  void printByZigZag(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> dq = new LinkedList<TreeNode>();
        int level = 1;
        boolean lr = true;
        TreeNode last = root;
        TreeNode nLast = null;
        dq.offerFirst(root);
        pringLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) {
                root = dq.pollFirst();
                if (root.left != null) {
                    nLast = nLast == null ? root.left : nLast;
                    dq.offerLast(root.left);
                }
                if (root.right != null) {
                    nLast = nLast == null ? root.right : nLast;
                    dq.offerLast(root.right);
                }
            } else {
                root = dq.pollLast();
                if (root.right != null) {
                    nLast = nLast == null ? root.right : nLast;
                    dq.offerFirst(root.right);
                }
                if (root.left != null) {
                    nLast = nLast == null ? root.left : nLast;
                    dq.offerFirst(root.left);
                }
            }
            System.out.print(root.value + " ");
            if (root == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                pringLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();
    }

    public static void pringLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 5, 3};
        int[] inOrder = new int[]{4, 2, 5, 1, 3};
        TreeNode root = ReConstructBinaryTree.buildTree(preOrder, 0, inOrder, inOrder.length - 1, inOrder.length);
        new IntuitivePrintTree().printTree(root);
        printByZigZag(root);
    }


}
