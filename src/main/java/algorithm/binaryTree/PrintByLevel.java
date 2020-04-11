package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @描述：按层打印二叉树
 * @思路：进行宽度优先遍历，使用队列queue存储当前层和下一层的节点信息； 即： 从queue的头部弹出节点node；若node有孩子，以“先左后右”的形式从尾部进入queue.
 * @复杂度：时间O(N) 空间O(1)
 * @链接：https://www.nowcoder.com/practice/6a1815a85bfc411d9295bc017e6b6dbe
 */
public class PrintByLevel {


    //宽度优先遍历基础版
    public static void printByLevel1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.printf(curr.value + " ");
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }
    }

    /*
     * 宽度优先遍历进阶版 - 按层换行打印
     * 如何确定换行？
     * 记录下"当前行的最右节点last" 和 "下一行的最右节点nlast"
     * 换行时刻：当前节点==last,然后更新last: last = nlast
     *
     * 结果形式：
     * Level 1：1
     * Level 2：2 3
     * Level 3：4 5
     */
    public static void printByLevel2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        int level = 1;
        TreeNode last = root; //当前行的最右节点
        TreeNode nlast = null; //下一行的最右节点

        queue.offer(root);
        System.out.print("\nLevel " + level++ + ":");
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.value + " ");

            if (curr.left != null) {
                queue.offer(curr.left);
                nlast = curr.left;
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                nlast = curr.right;
            }

            //换行时刻
            if (curr == last && !queue.isEmpty()) {
                last = nlast;
                System.out.print("\nLevel " + level++ + ":");
            }

        }

    }


    /**
     * 13
     * /   \
     * 65     5
     * /  \      \
     * 97  25     37
     * /  / \    /
     * 22  4  28  32
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = {13, 65, 5, 97, 25, null, 37, 22, null, 4, 28, null, null, 32, null};
        TreeNode root = TreeUtils.makeBinaryTreeByArray(arr);

        PrintByLevel.printByLevel1(root);
        PrintByLevel.printByLevel2(root);
    }


}
