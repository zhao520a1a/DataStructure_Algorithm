package algorithm.binaryTree;

import dataStructure.binaryTree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
* 深度优先遍历，广度优先遍历二叉树
* 递归和非递归先序，中序，后序遍历二叉树
* */
public class Traversal {


    /**
     * 深度优先遍历，相当于先序遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public void depthFirstTraversal(TreeNode head) {
        if (head == null) {
            System.out.println("empty tree");
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(head);
        while (stack.isEmpty() == false) {
            TreeNode treeNode = stack.pop();
            System.out.print(treeNode.value + "  ");
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
        System.out.print("\n");
    }

    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     */
    public void breadthFirstTraversal(TreeNode head) {
        if (head == null) {
            System.out.println("empty tree");
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(head);
        while (queue.isEmpty() == false) {
            TreeNode treeNode = queue.remove();
            System.out.print(treeNode.value + "  ");
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        System.out.print("\n");
    }




    /**
     * 先序遍历——递归实现
     */
    public void preOrderRecur(TreeNode head){
        if(head == null){
            return;
        }
        System.out.print(head.value + "  ");
        this.preOrderRecur(head.left);
        this.preOrderRecur(head.right);
    }

    /**
     * 中序遍历——递归实现
     */
    public void inOrderRecur(TreeNode head){
        if(head == null){
            return;
        }
        this.inOrderRecur(head.left);
        System.out.print(head.value + "  ");
        this.inOrderRecur(head.right);
    }

    /**
     * 后序遍历——递归实现
     */
    public void posOrderRecur(TreeNode head){
        if(head == null) {
            return;
        }
        this.posOrderRecur(head.left);
        this.posOrderRecur(head.right);
        System.out.print(head.value + "  ");
    }


    /*
    * 递归———>非递归
    * 核心思想：
    *   递归：利用函数栈来保存信息；
    *   非递归：用自己申请的数据结构（例如：stack）来代替函数栈；
    */


    /**
     * 先序遍历——非递归实现---压入顺序：弹出打印，先右节点再左节点
     * 思路：
     *      1.申请一个新栈，先将栈顶元素压入、
     *      2.在栈中将栈顶节点弹出、打印；
     *      3.再将栈顶的右元素（不为空）压入，为空就跳过该步骤;
     *      4.在将栈顶的左元素（不为空）压入，为空就跳过该步骤；
     *      重复上述过程2，3，4；直到栈为空停止；
     */
    public void preOrderUnRecur(TreeNode head) {
        if(head != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(head);
            while(!stack.empty()){
                head = stack.pop();
                System.out.print(head.value + "  ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if (head.left != null){
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 中序遍历——非递归实现---一直将左边界压入栈中，直到为空，弹出打印，换行（cur = node.right）
     * 思路：
     *      1.申请一个新栈；
     *      2.先将栈顶元素cur压入，将左边界依次压入栈中（cur = cur.left）；
     *      3.直到为空(cur==null)，栈顶弹出打印，换行（cur = node.right）,重复步骤2；
     *      直到（cur为空&&栈为空）停止；
     *      注：下面的代码中为写起来简介，重用h变量，起到了步骤中cur变量的作用；
     */
    public void inOrderUnRecur(TreeNode h){
        if(h != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while(h != null || !stack.empty()){
                if(h != null) {
                    stack.add(h);
                    h = h.left;
                } else {
                    h = stack.pop();
                    System.out.print(h.value + "  ");
                    h = h.right;
                }
            }
        }
    }




    /**
     * 后序遍历——非递归实现---只使用了一个栈
     * 思路：
     *      1.申请一个新栈；
     *      变量h:代表最近一次弹出并打印的节点，初始为头节点
     *      变量c:代表stack的栈顶节点，初始为null
     *      2.取栈顶后，分三种情况讨论：
     *        情况一：其左孩子非空，且其左右孩子都不等于h
     *        在情况一不满足时，情况二：其右孩子非空，且不等于h
     *        在情况一、二不满足时，情况三：弹出并打印栈顶值
     *      循环上述过程2，直到栈为空；
     */
    public void posOrderUnRecur(TreeNode h){
        if(h != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(h);
            TreeNode c = null;
            while(!stack.isEmpty()){
                c = stack.peek();
                if(c.left!=null && c.left!=h && c.right!=h) {
                    stack.push(c.left);
                } else if(c.right!=null && c.right != h) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + "  ");
                    h = c;
                }
            }
        }
    }

}
