package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

/**
 * 判断该二叉树是否是平衡二叉树
 * AVL_Tree定义:要么是空树，要么任何一个节点左右子树高度差的绝对值不超过1；
 * 解法的整体过程是二叉树的后序遍历；
 * 当前节点：node
 * 每次遍历node的左子树/右子树，收集两个信息：①左子树/右子树是否为AVL树  ②左子树/右子树的最大深度H
 * <p>
 * <p>
 * Created by golden on 2017/5/6 0006.
 */
public class IsAVLTree {

    public boolean isBalance(TreeNode root) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 1, res);
        return res[0];
    }

    /**
     * @param root
     * @param level
     * @param res
     * @return 子树的最大深度
     */
    private int getHeight(TreeNode root, int level, boolean[] res) {
        if (root == null) {
            return level;
        }

        int lH = getHeight(root.left, level + 1, res);  //先左
        if (!res[0]) {    //当其左子树不符合时，迅速退出
            return level;
        }

        int rH = getHeight(root.right, level + 1, res); //再右
        if (!res[0]) {   //当其右子树不符合时，迅速退出
            return level;
        }

        if (Math.abs(lH - rH) > 1) {  //不满足AVLTree的条件时
            res[0] = false;
        }

        return Math.max(lH,rH);
    }

}
