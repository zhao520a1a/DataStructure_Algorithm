package algorithm.binaryTree;

import dataStructure.binaryTree.LinkedBinaryTree;
import dataStructure.binaryTree.TreeNode;

/**
 * 作用：逆时针打印二叉树的边界节点
 * Created by golden on 2017/2/27 0027.
 */
public class PrintEdge {

    /**
     * 打印标准1:逆时针打印二叉树的边界（从头节点开始）
     */
    public void printEdge1(TreeNode head) {
        if (head == null) {
            return;
        }
        //1.初始化记录最左和最右的二维数组
        int height = getHeight(head, 0);
        TreeNode[][] edgeMap = new TreeNode[height][2];
        //2.获得最左和最右的节点
        setEdgeMap(head, 0, edgeMap);
        //3.打印
        for (int i = 0; i < edgeMap.length; i++) {  //自顶向下打印左边界
            System.out.print(edgeMap[i][0].value + "  ");
        }
        printLeafNodeInMap(head, 0, edgeMap); //打印树底端的叶子节点
        for (int i = edgeMap.length - 1; i > -1; i--) {  //自底向上打印右边界（要避免重复打印同一节点）
            if (edgeMap[i][0] != edgeMap[i][1]) {
                System.out.print(edgeMap[i][1].value + "  ");
            }
        }


    }

    /**
     * 打印既不是左边界，也不是右边界的树底端的叶子节点
     * 条件：
     * 不是左边界： h!=edgeMap[i][0]
     * 不是右边界： h!=edgeMap[i][1]
     * 叶子节点：h.left == null && h.right == null
     * @param h
     * @param i 当前层数
     * @param edgeMap
     */
    private void printLeafNodeInMap(TreeNode h, int i, TreeNode[][] edgeMap) {
        if (h == null) {
            return;
        }
        if (h.left == null && h.right == null && h != edgeMap[i][0] && h != edgeMap[i][1]) {
            System.out.print(h.value + "  ");
        }
        printLeafNodeInMap(h.left, i + 1, edgeMap);
        printLeafNodeInMap(h.right, i + 1, edgeMap);


    }

    /**
     * 通过一个二维数组记录得到的二叉树每一层最左和最右的节点；
     * edgeMap[i][0]:存最左节点
     * edgeMap[i][1]:存最右节点
     * 口诀：递归思想，最左判null先固定，最右递归总更新
     *
     * @param h
     * @param i 当前层数
     * @param edgeMap
     */
    private void setEdgeMap(TreeNode h, int i, TreeNode[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[i][0] = edgeMap[i][0] == null ? h : edgeMap[i][0];  //最左节点
        edgeMap[i][1] = h;   //最右节点
        setEdgeMap(h.left, i + 1, edgeMap);
        setEdgeMap(h.right, i + 1, edgeMap);
    }

    /**
     * 计算二叉树高度
     *
     * @param head
     * @param l    当前层数
     * @return
     */
    private int getHeight(TreeNode head, int l) {
        if (head == null) {
            return l;
        }
        return Math.max(getHeight(head.left, l + 1), getHeight(head.right, l + 1));
    }


    /**
     * 打印标准2:逆时针打印左边只打印左叶子，右边只打印右叶子
     */
     public void printEdge2(TreeNode head){
         if(head == null){
             return;
         }
         System.out.print(head.value + "  ");  //在找的过程中将找过的的节点都打印
         if(head.left != null && head.right != null) {  //找到一个左右孩子都有的节点
              printLeftEdge(head.left, true);
              printRightEdge(head.right, true);
         }  else{
             TreeNode treeNode = head.left != null ? head.left : head.right;
             printEdge2(treeNode); //不满足，递归继续找
         }

     }

    /**
     * 打印树h左子树上所有的叶子节点；
     * 和左边界延展路径上的节点
     * @param h 当前节点
     * @param print 是否打印
     */
    private void printLeftEdge(TreeNode h, boolean print) {
        if(h == null){
            return ;
        }
        if(print || (h.left==null && h.right==null)){  //因为是自上而下打印，故放在递归前， 若h是左边界延展路径节点或叶子，则打印；
            System.out.print(h.value + "  ");
        }
        printLeftEdge(h.left,print);
        printLeftEdge(h.right, print && h.left == null?true:false); //在树的左边界，若h的左孩子不空，则h的右孩子不可能满足打印条件；也就没必要继续下去；
    }

    /**
     * 打印树h右子树上所有的叶子节点；
     * 和右边界延展路径上的节点
     * @param h 当前节点
     * @param print
     */
    private void printRightEdge(TreeNode h, boolean print) {
        if(h == null){
            return ;
        }
        printRightEdge(h.left,print && h.right == null?true:false);//在树的右边界，若h的右孩子不空，则h的左孩子不可能满足打印条件；也就没必要继续下去；
        printRightEdge(h.right,print);
        if(print || (h.left == null && h.left== null)){   //因为是自下而上打印，故放在递归后， 若h是右边界延展路径节点或叶子，则打印；
            System.out.print(h.value + "  ");
        }

    }



   public static void main(String[] args) {

        /*
        构造出如下形式的二叉树：
        *                           1
        *               2                       3
        *                 4                  5     6
        *               7   8              9  10
        *                     11        12
        *                   13  14    15  16
        * 标准1打印输出： 1  2  4  7  11  13  14  15  16  12  10  6  3
        * 标准2打印输出： 1  2  4  7  13  14  15  16  10  6  3
        */

       LinkedBinaryTree<String> binTree = new LinkedBinaryTree("1");
       //依次添加节点
       TreeNode tn2 = binTree.add(binTree.getRoot(), "2" , true);
       TreeNode tn3 = binTree.add(binTree.getRoot(), "3" ,false );
       TreeNode tn4 = binTree.add(tn2, "4" , false);
       TreeNode tn5 = binTree.add(tn3, "5" , true);
       TreeNode tn6 = binTree.add(tn3, "6" , false);
       TreeNode tn7 = binTree.add(tn4, "7" , true);
       TreeNode tn8 = binTree.add(tn4, "8" , false);
       TreeNode tn9 = binTree.add(tn5, "9" , true);
       TreeNode tn10 = binTree.add(tn5, "10" , false);
       TreeNode tn11 = binTree.add(tn8, "11" , false);
       TreeNode tn12 = binTree.add(tn9, "12" , true);
       TreeNode tn13 = binTree.add(tn11, "13" , true);
       TreeNode tn14 = binTree.add(tn11, "14" , false);
       TreeNode tn15 = binTree.add(tn12, "15" , true);
       TreeNode tn16 = binTree.add(tn12, "16" , false);

       new PrintEdge().printEdge1( binTree.getRoot());

       new PrintEdge().printEdge2( binTree.getRoot());


   }

}
