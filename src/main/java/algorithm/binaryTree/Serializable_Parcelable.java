package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化：
 *    序列化：将二叉树记录成文件的过程
 *    反序列化：通过文件内容重建原来二叉树的过程
 * 自定义表示符号：  例：#！2!#!6!
 *      "#" --- 代表是空节点
 *      "!" --- 结束符（代表一个值的结束）
 * Created by golden on 2017/3/1 0001.
 */
public class Serializable_Parcelable {


    /*通过先序遍历实现序列化和反序列化：用递归实现*/
    /**
     *  序列化
     * @param head
     * @return
     */
    public String serialByPre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }


    /**
     * 反序列化
     * @param preStr
     * @return
     */
    public TreeNode parcelByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        //将内容添加到队列中
        for (int i = 0; i != values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    public TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


    /*通过层遍历实现序列化和反序列化: 用while循环实现*/
    /**
     * 序列化
     *
     * @param head
     * @return
     */
    public String serialByLevel(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            //先记录左孩子
            if (head.left != null) {
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            //在记录右孩子
            if (head.right != null) {
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    /**
     * 反序列化
     * @param levelStr
     * @return
     */
    public TreeNode parcelByLevevlString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        //找到第一个非空的节点
        TreeNode head = generateNodeByString(values[index++]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (head != null) {
            queue.offer(head);
        }

        TreeNode treeNode = null;
        while (!queue.isEmpty()) {
            treeNode = queue.poll();
            treeNode.left = generateNodeByString(values[index++]);
            treeNode.right = generateNodeByString(values[index++]);
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
        return head;
    }

    public TreeNode generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }


}
