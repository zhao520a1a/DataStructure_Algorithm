//package algorithm.binaryTree;
//
//import java.util.*;
//
///**
// * Created by golden on 2016/9/27 0027.
// * 遍历二叉树：
// * 深度优先遍历二叉树
// * 广度优先遍历二叉树
// * 先序，中序，后序遍历二叉树；
// */
//
//public class TraversalBinaryTree1 {
//
//
//
//
//
//
//
//
//    /**
//     * 深度优先遍历，相当于先根遍历
//     * 采用非递归实现
//     * 需要辅助数据结构：栈
//     */
//    public void depthFirstTraversal() {
//        if (root == null) {
//            System.out.println("empty tree");
//            return;
//        }
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//        stack.push(root);
//        while (stack.isEmpty() == false) {
//            TreeNode node = stack.pop();
//            System.out.print(node.value + "    ");
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }
//        System.out.print("\n");
//    }
//
//    /**
//     * 广度优先遍历
//     * 采用非递归实现
//     * 需要辅助数据结构：队列
//     */
//    public void breadthFirstTraversal() {
//        if (root == null) {
//            System.out.println("empty tree");
//            return;
//        }
//        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
//        queue.add(root);
//        while (queue.isEmpty() == false) {
//            TreeNode node = queue.remove();
//            System.out.print(node.value + "    ");
//            if (node.left != null) {
//                queue.add(node.left);
//            }
//            if (node.right != null) {
//                queue.add(node.right);
//            }
//        }
//        System.out.print("\n");
//    }
//
//    public void printNode(TreeNode node){
//        System.out.print(node.value+"  ");
//    }
//
//    //先序遍历:先根结点，再先序左子树，再先序右子树；递归算法；  DLR
//    public void preorderTraversal(TreeNode node) {
//        printNode(node);
//        if(node.left != null) {
//            this.preorderTraversal(node.left);
//        }
//        if(node.right != null) {
//            this.preorderTraversal(node.right);
//        }
//    }
//
//    //中序遍历：先中序遍历左子树，再访问根节点，再中序遍历右子树； LDR
//    public  void inorderTraversal(TreeNode node) {
//        if(node.left != null) {
//            this.inorderTraversal(node.left);
//        }
//        printNode(node);
//        if(node.right != null) {
//            this.inorderTraversal(node.right);
//        }
//    }
//
//    //后序遍历：先中序遍历左子树，再中序遍历右子树，再访问根节点；  LRD
//    public void postorderTraversal(TreeNode node) {
//        if(node.left != null) {
//            this.postorderTraversal(node.left);
//        }
//        if(node.right != null) {
//            this.postorderTraversal(node.right);
//        }
//        printNode(node);
//    }
//
//
//    /**
//     *        13
//     *      /   \
//     *    65     5
//     *  /  \      \
//     * 97  25     37
//     * /  / \    /
//     22  4  28  32
//     *
//     深度遍历：   13    65    97    22    25    4    28    5    37    32
//     广度遍历：   13    65    5    97    25    37    22    4    28    32
//     先序遍历：    13  65  97  22  25  4  28  5  37  32
//     中序遍历：    22  97  65  4  25  28  13  5  32  37
//     后序遍历：    22  97  4  28  25  65  32  37  5  13
//     */
//    public static void main(String[] args) {
//        Integer[] arr = {13, 65, 5, 97, 25, null, 37, 22, null, 4, 28, null, null, 32, null};
//        TraversalBinaryTree1 tree = new TraversalBinaryTree1(arr);
//        System.out.println("深度遍历：");
//        tree.depthFirstTraversal();
//        System.out.println("广度遍历：");
//        tree.breadthFirstTraversal();
//
//        TreeNode root = tree.getRoot();
//        System.out.println("先序遍历：");
//        tree.preorderTraversal(root);
//        System.out.println("\n" + "中序遍历：");
//        tree.inorderTraversal(root);
//        System.out.println("\n" +"后序遍历：");
//        tree.postorderTraversal(root);
//    }
//
//
//}
