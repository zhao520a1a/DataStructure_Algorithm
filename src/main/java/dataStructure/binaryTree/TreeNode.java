package dataStructure.binaryTree;


public class TreeNode<T> {
    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }

    public TreeNode() {
    }

    public TreeNode(TreeNode left, T value, TreeNode right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }


    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int i = getHeight(root.left);
        int j = getHeight(root.right);
        return (i < j) ? j + 1 : i + 1;
    }


    public static int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getSize(root.left) + getSize(root.right);
    }

}
