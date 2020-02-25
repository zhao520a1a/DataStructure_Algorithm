package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

import java.util.HashMap;

/**
 * 通过先序，中序，后序数组；两两组合搭配重构二叉树
 * Created by golden on 2017/4/29 0029.
 */
public class ReConstructBinaryTree {


    /**
     * 先序 +　中序
     * 思路： 用先序最左值对中序数组进行划分;用HashMap来存储中序数组，方便查找某值在数组中所在的位置；
     *
     * @param pre 先序数组
     * @param in  中序数组
     * @return 根结点
     */
    public TreeNode preInToTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);  //存储中序数组：key-数值    value-位置
        }
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public TreeNode preIn(int[] pre, int pstart, int pend, int[] in, int istart, int iend, HashMap<Integer, Integer> map) {
        if (pstart > pend) {
            return null;
        }
        TreeNode head = new TreeNode(pre[pstart]);

        // pstart：当前结点在先序数组中位置； index：当前结点在中序数组中位置
        int index = map.get(pre[pstart]);  //得到先序头结点，在中序中所在位置

        // 当前结点的左子树中序数组 在整个中序数组中的区间范围： 【istart,index - 1, 】
        //重点理解：length = index-istart 表示的是当前结点左子树的中序数组长度 == 当前结点左子树的先序数组长度（即：先序中它左边的length个数，就是它左子树的先序数组）
        // 当前结点的左子树先序数组 在整个先序数组中的区间范围： 【 pstart + 1,  pstart + (index - istart)】
        head.left = preIn(pre, pstart + 1, pstart + index - istart, in, istart, index - 1, map);
        // 当前结点的右子树中序数组 在整个中序数组中的区间范围： 【index+1,iend, 】
        // 当前结点的右子树先序数组 在整个先序数组中的区间范围： 【 pstart + (index-istart) + 1,  pend】
        head.right = preIn(pre, pstart + index - istart + 1, pend, in, index + 1, iend, map);
        return head;
    }

    /**
     * 中序 +　后序
     * 思路： 用后序最右值对中序数组进行划分;用HashMap来存储中序数组，方便查找某值在数组中所在的位置；
     *
     * @param in  中序数组
     * @param pos 后序数组
     * @return 根结点
     */
    public TreeNode inPosToTree(int[] in, int[] pos) {
        if (in == null || pos == null) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return inPos(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
    }

    private TreeNode inPos(int[] in, int istart, int iend, int[] pos, int pstart, int pend, HashMap<Integer, Integer> map) {
        if (pstart > pend) {
            return null;
        }
        TreeNode head = new TreeNode(pos[pend]);

        // pend：当前结点在后序数组中位置；  index：当前结点在中序数组中位置
        int index = map.get(pos[pend]);
        // 当前结点的左子树中序数组 在整个中序数组中的区间范围： 【istart,index - 1 】
        //重点理解：length = index-istart 表示的是当前结点左子树的中序数组长度 == 当前结点左子树的后序数组长度（即：后序中从头开始的length个数，就是它左子树的后序数组）
        // 当前结点的左子树后序数组 在整个后序数组中的区间范围： 【 pstart,  pstart + (index - istart) -1】 注：因为pstart该位置的值是在左子树的后序数组，因此要-1来获得正确的数组长度
        head.left = inPos(in, istart, index - 1, pos, pstart, pstart + index - istart - 1, map);
        // 当前结点的右子树中序数组 在整个中序数组中的区间范围： 【index+1,iend, 】
        // 当前结点的右子树后序数组 在整个后序数组中的区间范围： 【 pstart + (index-istart),  pend-1】
        head.right = inPos(in, index + 1, iend, pos, pstart - (index - istart), pend - 1, map);
        return head;
    }


    /**
     * 前序 +　后序 :在大多数情况下不能通过这两个数组把树还原出来；
     * 能重构成功的条件：除叶子节点外，其他所有节点都有左孩子和右孩子；
     * <p>
     * 思路： 通过划分左右子树各自的先序和后序数组的方式重建整棵树;用HashMap来存储后序数组，方便查找某值在数组中所在的位置；
     *        先序中当前节点左子树的根节点 在后序数组中位置，划分出当前节点的左右子树范围
     * @param pre 先序数组
     * @param pos 后序数组
     * @return 根结点
     */
    public TreeNode prePosToTree(int pre[], int[] pos) {
        if (pre == null || pos == null) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < pos.length; i++) {
            map.put(pos[i], i);
        }
        return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
    }

    private TreeNode prePos(int[] pre, int rstart, int rend, int[] pos, int ostart, int oend, HashMap<Integer, Integer> map) {
        TreeNode head = new TreeNode(pos[oend--]); //从后序右侧获得当前节点
        if (rstart == rend) {
            return head;
        }

        // ++rstart：当前结点的左子树的根节点 在先序数组中位置；  index：当前结点的左子树的根节点 在后序数组中位置
        int index = map.get(pre[++rstart]);
        //重点理解：length = index-ostart 表示的是当前结点左子树的后序数组长度 == 当前结点左子树的先序数组长度（即：先序中它左边的length个数，就是它左子树的先序数组）
        // 当前结点的左子树先序数组 在整个先序数组中的区间范围： 【rstart,rstart + （index - ostart） 】
        // 当前结点的左子树后序数组 在整个后序数组中的区间范围： 【 ostart,index】
        head.left = prePos(pre, rstart, rstart + index - ostart, pos, ostart, index, map);
        // 当前结点的右子树先序数组 在整个先序数组中的区间范围： 【 rstart + (index - ostart) + 1 ,rend, 】
        // 当前结点的右子树后序数组 在整个后序数组中的区间范围： 【 index+1,oend】
        head.right = prePos(pre, rstart + index - ostart + 1, rend, pos, index + 1, oend, map);
        return head;
    }




    /**
     * 扩展：
     * 根据前序遍历和中序遍历重建二叉树子树
     * @param preOrder 前序遍历数组
     * @param start 子树起始位置
     * @param inOrder 中序遍历数组
     * @param end 中序遍历结束位置
     * @param length 子树节点树
     * @return 子树的根节点
     */
    public static TreeNode buildTree(int[] preOrder, int start,
                                     int[] inOrder, int end, int length) {
        //参数验证
        if (preOrder == null || preOrder.length == 0 || inOrder == null
                || inOrder.length == 0 || length <= 0) {
            return null;
        }
        //建立子树根节点
        int value = preOrder[start];
        TreeNode root = new TreeNode();
        root.value = value;
        //递归终止条件：子树只有一个节点
        if (length == 1)
            return root;
        //分拆子树的左子树和右子树
        int i = 0;
        while (i < length) {   /*这里可以用HashMap来存储，提高效率；*/
            if (value == inOrder[end - i]) {
                break;
            }
            i++;
        }
        //建立子树的左子树
        root.left = buildTree(preOrder, start + 1, inOrder, end - i - 1, length - 1 - i);
        //建立子树的右子树
        root.right = buildTree(preOrder, start + length - i, inOrder, end, i );
        return root;
    }



    public static void main(String[] args) {
        int[] preOrder = new int[] {1, 2, 4, 5, 3};
        int[] inOrder = new int[] {4 ,2, 5, 1, 3};
        TreeNode root = buildTree(preOrder, 0, inOrder, inOrder.length - 1, inOrder.length);
        new IntuitivePrintTree().printTree(root);
    }




}
