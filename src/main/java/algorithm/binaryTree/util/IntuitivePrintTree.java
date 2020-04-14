package algorithm.binaryTree.util;

import dataStructure.binaryTree.LinkedBinaryTree;
import dataStructure.binaryTree.TreeNode;
import org.junit.Test;

/**
 * 直观的打印二叉树问题
 * 面试题：自定义格式和方法，使之较为较为直观的打印二叉树
 * 前提： 假设节点的值为整数类型
 * 1.设计打印样式，方法：将节点值加上表特殊意义的前后缀;
 * 例如：H1H，L2L,R3R
 * 含义如下：
 * H：代指头节点
 * L:代指为是上一个节点的左节点
 * R:代指为是上一个节点的右节点
 * 2.规定节点打印时占用的统一长度（ 11（整数位数） + 2(前后缀占位) + 4（为更好区分前后加2个空格）= 17），
 * 防止因格式对不齐，而产生歧义；
 * 3.具体实现：利用递归
 * 针对一个节点：
 * 先递归遍历它的右子树；
 * 再回到该节点，打印输出内容；
 * 最后递归遍历它的左子树；
 * 思考：
 * 递归为什么是先右后左呢？ 因为只有这样才能自上而下的打印出完整的二叉树
 * Created by golden on 2017/2/27 0027.
 */
public class IntuitivePrintTree {

    public static void printTree(TreeNode head) {
        System.out.println("自定义图形直观打印二叉树（将图形顺时针旋转90度看）：");
        printInOrder(head, 0, "H", 17);
    }

    /**
     * 实现直观的打印二叉树
     *
     * @param h      当前节点
     * @param height 树的高度
     * @param to     值的前后缀
     * @param len    规定节点打印时占用的统一长度
     */
    public static void printInOrder(TreeNode h, int height, String to, int len) {
        if (h == null) {
            return;
        }
        // 1.先递归遍历它的右子树；
        printInOrder(h.right, height + 1, "R", len);
        // 2.制作该节点的内容；
        String val = to + h.value + to; //将值构造成规定格式
        int lenM = val.length();  //规定的格式所占的位数
        int lenL = (len - lenM) / 2;  //计算出其前面应添的空格数量
        int lenR = len - lenM - lenL;  //计算出其后面应添的空格数量
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        // 3.最后递归遍历它的左子树
        printInOrder(h.left, height + 1, "L", len);
    }


    /**
     * 作用：添充空格
     *
     * @param num 空格数量
     * @return
     */
    public static String getSpace(int num) {
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(" ");
        }
        return buf.toString();
    }


    @Test
    public void test() {

        /*
        构造出如下形式的二叉树（将图形顺时针旋转90度看）：
        *                           1
        *               2                       3
        *                 4                  5     6
        *               7   8              9  10
        *                     11        12
        *                   13  14    15  16
        * 自定义图形直观打印二叉树：
                                         R6R
                        R3R
                                                         R10R
                                         L5L
                                                          L9L
                                                                                           R16R
                                                                          L12L
                                                                                           L15L
       H1H
                                                                                           R14R
                                                                          R11R
                                                                                           L13L
                                                          R8R
                                         R4R
                                                          L7L
                        L2L
        */

        LinkedBinaryTree<String> binTree = new LinkedBinaryTree("1");
        //依次添加节点
        TreeNode tn2 = binTree.add(binTree.getRoot(), "2", true);
        TreeNode tn3 = binTree.add(binTree.getRoot(), "3", false);
        TreeNode tn4 = binTree.add(tn2, "4", false);
        TreeNode tn5 = binTree.add(tn3, "5", true);
        TreeNode tn6 = binTree.add(tn3, "6", false);
        TreeNode tn7 = binTree.add(tn4, "7", true);
        TreeNode tn8 = binTree.add(tn4, "8", false);
        TreeNode tn9 = binTree.add(tn5, "9", true);
        TreeNode tn10 = binTree.add(tn5, "10", false);
        TreeNode tn11 = binTree.add(tn8, "11", false);
        TreeNode tn12 = binTree.add(tn9, "12", true);
        TreeNode tn13 = binTree.add(tn11, "13", true);
        TreeNode tn14 = binTree.add(tn11, "14", false);
        TreeNode tn15 = binTree.add(tn12, "15", true);
        TreeNode tn16 = binTree.add(tn12, "16", false);

        IntuitivePrintTree.printTree(binTree.getRoot());

    }


}
