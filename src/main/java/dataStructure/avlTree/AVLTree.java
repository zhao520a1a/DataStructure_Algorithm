package dataStructure.avlTree;

/**
 * Created by golden on 2016/10/21 0021.
 * AVL树的构造
 */
public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> root;    // 根结点

    // AVL树的节点(内部类)
    class AVLTreeNode<T extends Comparable<T>> {
        T value;                // 关键字(键值)
        int height;             // 高度
        AVLTreeNode<T> left;    // 左孩子
        AVLTreeNode<T> right;    // 右孩子

        public AVLTreeNode(T value, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    // 构造函数
    public AVLTree() {
        root = null;
    }

    /*
     *  选取两者中的较大数
     */
    private int larger(int a, int b) {
        return a > b ? a : b;
    }

    /*
     * 获取某节点的层次
     */
    private int height(AVLTreeNode<T> node) {
        return node != null ? node.height : 0;
    }
    /*
     * 获取树的高度
     */
    public int height() {
        return height(root);
    }

    private AVLTreeNode<T> min(AVLTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 获取AVL树中节点的最小值（即：最左的叶子节点的值）
     */
    public T min() {
        AVLTreeNode<T> p = min(root);
        if (p != null) {
            return p.value;
        }
        return null;
    }

    private AVLTreeNode<T> max(AVLTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /*
     * 获取AVL树中节点的最大值（即：最右的叶子节点的值）
     */
    public T max() {
        AVLTreeNode<T> p = max(root);
        if (p != null) {
            return p.value;
        }

        return null;
    }

    /*
     * 前序遍历"AVL树"
     */
    private void preOrder(AVLTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /*
     * 中序遍历"AVL树"
     */
    private void inOrder(AVLTreeNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    /*
     * 后序遍历"AVL树"
     */
    private void postOrder(AVLTreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 单向右旋平衡处理LL(插入的节点是左子树的左边节点)
     * 可能会现如下图的情况：
     *          2
     *         A                           B
     *       /  \                        /   \
     *      B    C         ----->>      D     A
     *     / \                        /     /   \
     *    D   E                      x     E     C
     *   /
     *  x
     * @param node 平衡因子等于2的节点 ( 例：上图中的A)
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> rightRotation(AVLTreeNode<T> node) {  // 例: 上图中的A节点
        AVLTreeNode  lChild = node.left;  //例：上图中的B节点
        node.left =  lChild.right;
        lChild.right = node;
        // 更新高度
        node.height = larger(height(node.left), height(node.right)) + 1;
        lChild.height = larger(height(lChild.left), node.height) + 1;
        return lChild;
    }

    /**
     * 单向左旋平衡处理RR(插入节点是右子树的右边节点)
     * @param node 平衡因子等于2的节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftRotation(AVLTreeNode<T> node) {
        AVLTreeNode rChild = node.right;
        node.right = rChild.left;
        rChild.left = node;
        // 更新高度
        node.height = larger(height(node.left), height(node.right)) + 1;
        rChild.height = larger(height(rChild.left), node.height) + 1;
        return rChild;
    }

    /**
     * 双向旋转（先右旋后左旋）平衡处理RL(插入节点是右子树的左边节点)
     * @param node 平衡因子等于2的节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    /**
     * 双向旋转（先左旋后右旋）平衡处理LR(插入节点是左子树的右边节点)
     * @param node 平衡因子等于2的节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     * @param node  AVL树中的结点
     * @param value 插入的结点的值
     * @return 根节点
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> node, T value) {
        if (node == null) {
            node = new AVLTreeNode<T>(value, null, null);
        } else {
            int result = value.compareTo(node.value);// 将新节点的值同它的node节点的值比较，从而决定是插入到左子树中还是右子树中
            if(result == 0 ) {
                System.out.println("添加失败：不允许添加相同的节点！");
            }
            if (result < 0) {
                node.left = insert(node.left, value);   //通过递归，最终获得将新节点插入到指定节点的左孩子位置；
                if (height(node.left) - height(node.right) == 2) {     //左子树中出现了不平衡的情况
                    if (value.compareTo(node.left.value) < 0) {   //将新节点的值和它的根节点的值进行比较 ，从而判断出到它是其根节点的左孩子还是右孩子
                        node = rightRotation(node);  // 为左孩子，则说明出现LL情况
                    } else {
                        node = rightLeftRotation(node);  //为右孩子，则说明出现LR情况
                    }
                }
            } else {
                node.right = insert(node.right, value);  //通过递归，最终获得将新节点插入到指定节点的右孩子位置；
                if (height(node.right) - height(node.left) == 2) {  //右子树中出现了不平衡的情况
                    if (value.compareTo(node.right.value) < 0) {
                        node = leftRightRotation(node);//  为左孩子，自说明出现RL情况
                    } else {
                        node = leftRotation(node);// 为右孩子，则说明出现RR情况
                    }
                }
            }
        }
        node.height = larger(height(node.left), height(node.right)) + 1;    //从0开始算高度
        return node;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    /*
     * 通过递归的方法来销毁AVL树
     */
    private void destroy(AVLTreeNode<T> node) {
        if(node == null) {
            return ;
        }
        if(node.left != null) {
            destroy(node.left);
        }
        if(node.right != null) {
            destroy(node.right);
        }
        node = null;
    }

    public void destroy() {
        destroy(root);
    }

    /**
     *  打印AVL树
     * @param node
     * @param value 节点的值
     * @param direction  0，表示该节点是根节点;   -1，表示该节点是它的父结点的左孩子;    1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLTreeNode<T> node, T value, int direction) {
        if (node != null) {
            if (direction == 0) {
                System.out.printf("%2d is root\n", node.value, value);
            }
            else {
                System.out.printf("%2d is %2d's %6s child\n", node.value, value, direction == 1 ? "right" : "left");
            }
            print(node.left, node.value, -1);
            print(node.right, node.value, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.value, 0);
    }


    public static void main(String[] args) {
        int arr[] = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};
        AVLTree<Integer> tree = new AVLTree<Integer>();

        for (int i = 0; i < arr.length; i++) {          //将数据依次添加到AVL树中:
            tree.insert(arr[i]);
        }
        System.out.printf("AVLTree高度: %d\n", tree.height());
        System.out.printf("AVLTree最小值: %d\n", tree.min());
        System.out.printf("AVLTree最大值: %d\n", tree.max());

        System.out.printf("AVLTree前序遍历: ");
        tree.preOrder();
        System.out.printf("\nAVLTree中序遍历: ");
        tree.inOrder();
        System.out.printf("\nAVLTree后序遍历: ");
        tree.postOrder();
        System.out.printf("\nAVLTree的详细信息: \n");
        tree.print();

        tree.destroy();     // 销毁二叉树
    }


}
