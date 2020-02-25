package algorithm.list;


import dataStructure.list.node.ListNode;

import java.util.Stack;

/**
 * 链表的回文结构
 * <p>
 * Created by golden on 2017/4/23 0023.
 */
public class PalindromeList {

    /**
     * 判断一个链表是否为回文结构；
     * 利用栈存储链表的右半区；
     * 将右半区元素弹出栈的顺序，同链表的左半区元素顺序进行比较，看顺序是否相同；
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        /*令right -> 右半区的第一个节点
        （即：链表长度N为奇数，right为第(N+1)/2+1个结点【忽略处于最中间的结点】;
              链表长度N为偶数，right为第N/2+1个结点*/
        ListNode cur = head;
        ListNode right = head.next;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;  // right ->中部
            cur = cur.next.next;  //cur-->尾部
        }

        /*将整个链表的右半部分压入栈中*/
        Stack<ListNode> stack = new Stack<ListNode>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        /*检查栈顶到栈底值出现的顺序时候和链表左半部分的值相同，完全相同则该链表为回文结构*/
        while (!stack.isEmpty()) {
            if (head.item != stack.pop().item) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    /**
     * 进阶解法：时间O(n)，空间O(1)
     * 举例：  1 -> 2 -> 3 -> 4 -> 5
     * 1.改变链表右半区结构：令反转右半区，最后指向链表中间的节点；
     * 即：1 -> 2 -> 3 <- 4 <- 5
     * 2.leftStart和rightStart同时向中间移动，比较相应结点的值，看是否满足回文结构；
     * 3.无论结果如何，都应该将链表恢复成原来的样子，然后在返回检查结果；
     */


    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode n1 = head;
        ListNode n2 = head;
        /*找到中间的结点，循环后结果为： n1 -> 中部;  n2->结尾*/
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n1.next.next;
        }
        n2 = n1.next; //  n2->右半区的第一个结点
        n1.next = null;


        ListNode n3 = null;
        /*反转右半区：结果：n1->最后一个结点，n2、n3=null*/
        while (n2 != null) {// n2表示当前结点,n1表示原结构中它的前一个结点,n3表示原结构中它的后一个结点
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;//移动
            n2 = n3;//移动
        }


        n3 = n1;    //n3->最后一个结点
        n2 = head;
        boolean res = true;
        /*通过依次比较值来检查是否符合回文结构*/
        while (n1 != null && n2 != null) {
            if (n1.item != n2.item) {
                res = false;
                break;
            }
            n1 = n1.next;  //由左向中移动
            n2 = n2.next;  //由右向中移动
        }


        /*恢复链表原本的结构*/
        n2 = n3.next;   //n2->最后一个结点的前一个结点
        n3.next = null;
        while (n2 != null) {// n2表示当前结点,n1表示原结构中它的前一个结点,n3表示原结构中它的后一个结点
            n1 = n2.next;
            n2.next = n3;
            n3 = n2; //移动
            n2 = n1;//移动
        }

        return res;
    }


}
