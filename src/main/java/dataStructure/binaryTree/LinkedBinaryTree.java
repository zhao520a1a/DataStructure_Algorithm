package dataStructure.binaryTree;


/**
 * Created by golden on 2016/9/27 0027.
 * 链式的二叉树实现；
 *
 */
public class LinkedBinaryTree<T> {



    private TreeNode<T> root;

    //以默认的构造器来创建二叉树
    public LinkedBinaryTree() {
    }
    //以指定根元素来创建二叉树
    public LinkedBinaryTree(T value) {
        root = new TreeNode(value);
    }

    /**
     * 为指定节点添加子节点。
     * @param parent 需要添加子节点的父节点的索引
     * @param value 新子节点的数据
     * @param isLeft 是否为左节点
     * @return 添加的子节点
     */
    public TreeNode<T> add (TreeNode<T> parent, T value, boolean isLeft) {
        if(parent == null) {
            throw new RuntimeException(parent + "节点为null,无法添加子节点");
        }
        if (isLeft && parent.left != null){//已经有左节点了
            throw new RuntimeException(parent + "节点已有左子节点，无法添加左子节点");
        }
        if (!isLeft && parent.right != null){//已经有右节点了
            throw new RuntimeException(parent +  "节点已有右子节点，无法添加右子节点");
        }
        TreeNode<T> newTreeNode = new TreeNode<T>(value);
        if(isLeft) {
            parent.left = newTreeNode;
        } else {
            parent.right = newTreeNode;
        }
        return newTreeNode;
    }

    //返回指定节点（非叶子）的左子节点。
    public T leftChild(TreeNode<T> parent){
        if (parent == null){
            throw new RuntimeException(parent +   "节点为null，无法获取子节点");
        }
        return parent.left == null ? null : (T)parent.left.value;
    }

    //返回指定节点（非叶子）的右子节点。
    public  T rightChild(TreeNode<T> parent){
        if (parent == null)	{
            throw new RuntimeException(parent +  "节点为null，无法获取子节点");
        }
        return parent.right == null ? null : (T)parent.right.value;
    }

    //判断二叉树是否为空。
    public boolean empty(){
        return root == null;
    }
    //返回根节点。
    public TreeNode<T> getRoot(){
        if ( empty()){
            throw new RuntimeException("树为空，无法访问根节点");
        }
        return root;
    }

    // 二叉树的深度=取所有子节点的最大层次
    public int getDeep(){
        return getDeep(root);
    }

    // 递归的方式返回某个节点的层次(取其左右子节点的最大层次+1)
    public int getDeep (TreeNode<T> treeNode) {
        if (treeNode == null) {
            return 0;
        }
        if(treeNode.left == null && treeNode.right == null) {
            return 1;
        } else {
            int leftDeep = getDeep(treeNode.left);  //左子树深度
            int rightDeep = getDeep(treeNode.right);  //右子树深度
            int max = leftDeep > rightDeep ? leftDeep : rightDeep; //取两者的较大值
            return max + 1;   //返回其左右子树中较大的深度 + 1
        }
    }

}