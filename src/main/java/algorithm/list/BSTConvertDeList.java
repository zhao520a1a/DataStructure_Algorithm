package algorithm.list;



import dataStructure.binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将二叉搜索树转化为双向链表；时间O(N) 空间O(N)
 * 发现：二叉树的结点双向链表结点结构相同，因此在转换上较为方便；
 * 思想：利用队列存二叉树中序遍历的结果，按照队列弹出顺序连接所有节点即可；
 * Created by golden on 2017/4/26 0026.
 */
public class BSTConvertDeList {
    public TreeNode Convert(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        //中序遍历二叉树
        inOrderToQueue(root, queue);
        //按照队列弹出顺序连接所有节点
        if(queue.isEmpty()){
            return root;
        }
        root = queue.poll();
        TreeNode pre = root;
        pre.left = null;
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            /*互指*/
            pre.right = cur;
            cur.left = pre;
            pre = cur;//移动
        }
        pre.right = null;
        return root;
    }

    public void inOrderToQueue(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        inOrderToQueue(root.left, queue);
        queue.offer(root);
        inOrderToQueue(root.right, queue);
    }
}
