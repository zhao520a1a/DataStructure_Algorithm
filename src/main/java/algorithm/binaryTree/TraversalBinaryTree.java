package algorithm.binaryTree;

import algorithm.binaryTree.util.TreeUtils;
import dataStructure.binaryTree.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @描述：先序、中序、后序遍历二叉树
 * @思路：
 * @复杂度： 时间O(n) 【因为它们只访问每个节点一次，不存在多余的访问】
 * @链接： https://www.nowcoder.com/practice/566f7f9d68c24691aa5abd8abefa798c
 */
class TraversalBinaryTree {


    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        if (root.left != null) {
            preOrderTraversal(root.left);
        }
        if (root.right != null) {
            preOrderTraversal(root.right);
        }
    }


    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrderTraversal(root.left);
        }
        System.out.print(root.value + " ");
        if (root.right != null) {
            inOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrderTraversal(root.left);
        }
        if (root.right != null) {
            postOrderTraversal(root.right);
        }
        System.out.print(root.value + " ");
    }


    /**
     * 13
     * /   \
     * 65     5
     * /  \      \
     * 97  25     37
     * /  / \    /
     * 22  4  28  32
     * <p>
     * 深度遍历：   13    65    97    22    25    4    28    5    37    32
     * 广度遍历：   13    65    5    97    25    37    22    4    28    32
     * 先序遍历：    13  65  97  22  25  4  28  5  37  32
     * 中序遍历：    22  97  65  4  25  28  13  5  32  37
     * 后序遍历：    22  97  4  28  25  65  32  37  5  13
     */
    public static void main(String[] args) {
        Integer[] arr = {13, 65, 5, 97, 25, null, 37, 22, null, 4, 28, null, null, 32, null};
        TreeNode root = TreeUtils.makeBinaryTreeByArray(arr);

        System.out.println("先序遍历：");
        preOrderTraversal(root);
        System.out.println("\n" + "中序遍历：");
        inOrderTraversal(root);
        System.out.println("\n" + "后序遍历：");
        postOrderTraversal(root);

//        System.out.println("深度遍历：");
//        tree.depthFirstTraversal();
//        System.out.println("广度遍历：");
//        tree.breadthFirstTraversal();

    }


}


class Main1 {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] firstRow = in.readLine().split(" ");
        int row = Integer.parseInt(firstRow[0]);

        TreeNode root = TreeUtils.makeBinaryTreeByInput(in);
//        System.out.println("先序遍历：");
        TraversalBinaryTree.preOrderTraversal(root);
//        System.out.println("\n" + "中序遍历：");
        System.out.println();
        TraversalBinaryTree.inOrderTraversal(root);
//        System.out.println("\n" + "后序遍历：");
        System.out.println();
        TraversalBinaryTree.postOrderTraversal(root);
    }

}