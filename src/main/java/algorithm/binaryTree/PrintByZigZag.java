package algorithm.binaryTree;

import algorithm.binaryTree.util.TreeUtils;
import dataStructure.binaryTree.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @描述： 按Z字形按层打印二叉树的节点
 * @思路：只使用一个双端队列dq
 * 定义规则：
 * 规则1：若顺序是从左到右，从dq的头部弹出节点node；若node有孩子，以“先左后右”的形式从尾部进入dq.
 * 规则2：若顺序是从右到左，从dq的尾部弹出节点node；若node有孩子，以“先右后左”的形式从头部进入dq.
 * 确定规则1和规则2转换时机：（实质是一个确定每一层最后一个节点的问题）
 * 经总结发现：**下一层最后打印的节点 == 当前层有孩子节点中最先进入dq的节点 **
 * @复杂度：时间O(w)【其中w是二叉树的宽度（拥有最多节点的层的节点数）】
 * @链接： https://www.nowcoder.com/practice/6a1815a85bfc411d9295bc017e6b6dbe
 */
public class PrintByZigZag {


    public static <T> void printByZigZag(TreeNode<T> root) {
        Deque<TreeNode<T>> queue = new LinkedList<>();
        boolean isLeft = true;
        TreeNode last = root;
        TreeNode nlast = null;
        int level = 1;

        queue.add(root);
        pringLevelAndOrientation(level++, isLeft);

        while (!queue.isEmpty()) {
            TreeNode curr = null;
            if (isLeft) {
                curr = queue.pollFirst();
                if (curr.left != null) {
                    queue.addLast(curr.left);
                    nlast = nlast == null ? curr.left : nlast;
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                    nlast = nlast == null ? curr.right : nlast;
                }
            } else {
                curr = queue.pollLast();
                if (curr.right != null) {
                    queue.addFirst(curr.right);
                    nlast = nlast == null ? curr.right : nlast;
                }
                if (curr.left != null) {
                    queue.addFirst(curr.left);
                    nlast = nlast == null ? curr.left : nlast;
                }
            }
            System.out.print(curr.value + " ");

            //换行时刻(注：其中!queue.isEmpty()判断只是为了打印好看,可忽略)
            if (curr == last && !queue.isEmpty()) {
                last = nlast;
                nlast = null;
                isLeft = !isLeft;

                System.out.println();
                pringLevelAndOrientation(level++, isLeft);
            }
        }

    }


    //只是为了打印好看
    public static void pringLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right：" : "right to left：");
    }


    /**
     * 13
     * /   \
     * 65     5
     * /  \      \
     * 97  25     37
     * /  / \    /
     * 22  4  28  32
     */
    public static void main(String[] args) {
        Integer[] arr = {13, 65, 5, 97, 25, null, 37, 22, null, 4, 28, null, null, 32, null};
        TreeNode<Integer> root = TreeUtils.makeBinaryTreeByArray(arr);
        PrintByZigZag.printByZigZag(root);
    }


}


class Main2 {
    /**
     * 测试用例：
     * 8 1
     * 1 2 3
     * 2 4 0
     * 4 0 0
     * 3 5 6
     * 5 7 8
     * 7 0 0
     * 8 0 0
     * 6 0 0
     */
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] firstRow = in.readLine().split(" ");
        int row = Integer.parseInt(firstRow[0]);
        TreeNode root = TreeUtils.makeBinaryTreeByInput(in);

        //按层打印
        PrintByLevel.printByLevel2(root);

        System.out.println();

        //之字形打印
        PrintByZigZag.printByZigZag(root);

    }

}
