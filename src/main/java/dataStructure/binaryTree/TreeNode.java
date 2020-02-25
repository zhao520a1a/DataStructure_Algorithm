package dataStructure.binaryTree;

/**
 * 遍历二叉树
 * Created by golden on 2017/2/25 0025.
*/

public class TreeNode<T> {
   public T value;
   public TreeNode<T> left;
   public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }
    public TreeNode( ) {
    }

    public TreeNode(TreeNode left, T value, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
