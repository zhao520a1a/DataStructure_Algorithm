package algorithm.binaryTree.util;

import dataStructure.binaryTree.TreeNode;

import java.io.BufferedReader;


public class TreeUtils {

    /**
     * 采用非递归的方式创建一颗二叉树
     */
    public static <T> TreeNode<T> makeBinaryTreeByArray(T[] items) {
        return GenerateBinaryTree.makeBinaryTreeByArray(items);
    }


    /**
     * 采用递归的方式创建一颗二叉树
     */
    public <T> TreeNode makeBinaryTreeByArray(T[] items, int index) {
        return GenerateBinaryTree.makeBinaryTreeByArray(items, index);
    }


    /**
     * 通过控制台输入的方式，采用递归的方式创建一颗二叉树
     */
    public static TreeNode<String> makeBinaryTreeByInput(BufferedReader in) throws Exception {
        return GenerateBinaryTree.makeBinaryTreeByInput(in);
    }


    /**
     * 直观打印二叉树（将图形顺时针旋转90度看）
     */
    public void printTree(TreeNode root) {
        IntuitivePrintTree.printTree(root);
    }


}
