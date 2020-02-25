package algorithm.binaryTree;


import dataStructure.binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;



/**
 * 按层打印二叉树：
 * 结果形式：
 * Level 1：1
 * Level 2：2 3
 * Level 3：4 5
 * 思路：进行宽度优先遍历，使用宽度优先队列queue存储当前层和下一层的节点信息；
 * 即：从queue的头部弹出节点node；若node有孩子，以“先左后右”的形式从尾部进入queue.
 * 难点在于换行的确定；
 * last:正在打印的当前行的最右节点
 * nlast:下一行的最右节点
 * 换行时刻：遍历到的节点==last,然后让 last=nlast
 * nlast的更新：为宽度优先队列中最新加入的节点；
 * <p>
 * Created by golden on 2017/4/1 0001.
 */

public class PrintByLevel {

    public static void printByLeve(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList();

        int level = 1;
        TreeNode last = head;//初始化
        TreeNode nLast = null;//初始化

        queue.offer(head);
        System.out.print("\nLevel " + (level++) + "：");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
                nLast = head.left;   //跟踪最新加入的节点
            }
            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;  //跟踪最新加入的节点
            }
            if (head == last && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level++) + "：");
                last = nLast;   //换行后的操作
            }
        }
    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 5, 3};
        int[] inOrder = new int[]{4, 2, 5, 1, 3};
        TreeNode root = ReConstructBinaryTree.buildTree(preOrder, 0, inOrder, inOrder.length - 1, inOrder.length);
        new IntuitivePrintTree().printTree(root);
        printByLeve(root);
    }

}
