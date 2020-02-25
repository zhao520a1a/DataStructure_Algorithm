package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

/**  遍历二叉树的神级方法：
 * 用Morris算法完成二叉树的先序、中序和后序遍历；
 * <p>
 * 遍历二叉树的神级方法：Morris遍历
 * 时间复杂度O(N)
 * 空间复杂度O(1)  --- 神级之处
 * 普通的递归或非递归解法，空间复杂度为O(h),注：h为二叉树的高度
 * 介绍：
 * Morris遍历的实质：避免使用栈结构去遍历，而是让下层到上层有指针；
 * 利用某些节点指向null的right指针(因为该节点没有右孩子）
 * Created by golden on 2017/2/28 0028.
 */
public class MorrisTraversal {

    /**
     * 中序遍历
     */
    public void morrisIn(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;  //当前节点
        TreeNode cur2 = null; //用来表示当前节点的左孩子

        while (cur1 != null) {
            //步骤1：处理当前节点的左子树部分
            cur2 = cur1.left;
            if (cur2 != null) {
                //1.1+1.2-->将当前节点左子树中最右节点指向过当前节点
                //1.1找到当前节点左子树中最右节点（条件：当前节点有右孩子 && 2.2情况2：当前节点左子树中最右节点的right指针 并不指向 当前节点）
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) { // right为空，（下层指上层）
                    cur2.right = cur1; //1.2让最右节点的right指针指向当前节点----核心点
                    //下面两句是使：继续向左子树方向移动，使之重复步骤1.1，1.2；直到当前节点没有左子树停止；
                    cur1 = cur1.left;
                    continue;
                } else {    // 2.1情况1：若当前节点左子树中最右节点的right指针指向当前节点，将指针right置为空（将下层指上层指针去掉） ：
                    cur2.right = null;
                }
            }

            //步骤2：当前节点没有左子树时，当这种情况发生时，说明其左子树和本身已经打印完毕，开始其右子树的处理；
            System.out.print(cur1.value + "  "); //打印当前节点
            cur1 = cur1.right;  // 向右子树方向移动
        }
    }


    /**
     * 先序遍历：将中序遍历简单的改写
     * 只要把打印时机放在步骤1发生的时候即可；
     *
     * @param head
     */
    public void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.print(cur1.value + "  "); //----
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                //--
                System.out.print(cur1.value + "  ");
                //--
            }
            cur1 = cur1.right;
        }
    }

    /**
     * 后序遍历：对中序遍历进行改写，包含了更复杂的调整过程
     * 从逻辑上，就是依次逆序打印所有节点的左子树的右边界，打印的时机放在步骤2中情况1被触发的时候
     *
     * @param head
     */
    public void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);  //----依次逆序打印所有节点的左子树的右边界
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
    }

    /**
     * 依次逆序打印特定节点的右边界
     *
     * @param treeNode
     */
    public void printEdge(TreeNode treeNode) {
        TreeNode tail = reverseEdge(treeNode);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.value + "  ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**
     * 将指定节点的左子树的右边界最后一个节点
     *
     * @param from
     * @return
     */
    public TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }


}
