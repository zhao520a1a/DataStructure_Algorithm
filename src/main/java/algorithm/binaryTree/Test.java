package algorithm.binaryTree;


import algorithm.binaryTree.util.TreeUtils;
import dataStructure.binaryTree.TreeNode;

/**
 * Created by golden on 2016/9/27 0027.
 */
public class Test {
    public static void main(String[] args) {
       /*
       ArrayBinTree<string> binTree = new ArrayBinTree<string>(4, "root");
        binTree.add(0 , "第二层右子节点" , false);
        binTree.add(2 , "第三层右子节点" , false);
        binTree.add(6 , "第四层右子节点" , false);
        System.out.println(binTree);
        System.out.println(binTree.leftChild(6));//因为它的左面没有返回null
        System.out.println(binTree.rightChild(6));//第三层的右面就是第四层右节点
        System.out.println( binTree.getDeep());
        System.out.println("根节点：" + binTree.getRoot());
        */
        /*
        LinkedBinaryTree<string> binTree = new LinkedBinaryTree("根节点");
        //依次添加节点
       com.xin.binaryTree.TreeNode tn1 = binTree.add(binTree.getRoot(), "第二层左节点" , true);
       com.xin.binaryTree.TreeNode tn2 = binTree.add(binTree.getRoot(), "第二层右节点" ,false );
       com.xin.binaryTree.TreeNode tn3 = binTree.add(tn2 , "第三层左节点" , true);
       com.xin.binaryTree.TreeNode tn4 = binTree.add(tn2, "第三层右节点" , false);
       com.xin.binaryTree.TreeNode tn5 = binTree.add(tn3, "第四层左节点" , true);
        System.out.println("tn2的左子节点：" + binTree.leftChild(tn2));
        System.out.println("tn2的右子节点：" + binTree.rightChild(tn2));
        System.out.println(binTree.getDeep());
        */


        /**
         *        13
         *      /   \
         *    65     5
         *  /  \      \
         * 97  25     37
         * /  / \    /
         22  4  28  32
         *
         深度遍历（栈）：   13    65    97    22    25    4    28    5    37    32
         广度遍历（队列）：   13    65    5    97    25    37    22    4    28    32
         先序遍历：    13  65  97  22  25  4  28  5  37  32
         中序遍历：    22  97  65  4  25  28  13  5  32  37
         后序遍历：    22  97  4  28  25  65  32  37  5  13
         */
        Integer[] arr = {13, 65, 5, 97, 25, null, 37, 22, null, 4, 28, null, null, 32, null};
        TreeNode root = TreeUtils.makeBinaryTreeByArray(arr);

        Traversal traversal = new Traversal();
        System.out.println("深度遍历：");
        traversal.depthFirstTraversal(root);
        System.out.println("广度遍历：");
        traversal.breadthFirstTraversal(root);

        System.out.println("\n" + "递归先序遍历：");
        traversal.preOrderRecur(root);
        System.out.println("\n" + "非递归先序遍历：");
        traversal.preOrderUnRecur(root);

        System.out.println("\n" + "递归中序遍历：");
        traversal.inOrderRecur(root);
        System.out.println("\n" + "非递归中序遍历：");
        traversal.inOrderUnRecur(root);

        System.out.println("\n" + "递归后序遍历：");
        traversal.posOrderRecur(root);
        System.out.println("\n" + "非递归后序遍历：");
        traversal.posOrderUnRecur(root);

        MorrisTraversal morris = new MorrisTraversal();
        System.out.println("\n" + "Morris先序遍历：");
        morris.morrisPre(root);
        System.out.println("\n" + "Morris中序遍历：");
        morris.morrisIn(root);
        System.out.println("\n" + "Morris后序遍历：");
        morris.morrisPos(root);
    }


}

