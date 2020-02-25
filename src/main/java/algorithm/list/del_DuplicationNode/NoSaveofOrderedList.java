package algorithm.list.del_DuplicationNode;

import dataStructure.list.MyLinkedList;
import dataStructure.list.node.ListNode;
import org.junit.Test;

/**
 * 在排序的链表中删除重复的结点，重复的结点不保留，返回链表头指针。
 * <p>
 * 例：1->1->2->2->4->5->null
 *     4->5->null
 * 1.这题注意第一是排序链表，故重复的链表都连在一起
 * 2.注意首节点可能被删除，应有一个参数记录下头结点，要针对头结点重复的元素情况分类讨论
 * <p>
 * Created by golden on 2017/5/6 0006.
 */
public class NoSaveofOrderedList {

    /**
     * 方法一：利用循环；
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) return null;
        ListNode cur = pHead;
        ListNode n = new ListNode(0);//新建一个节点，作用是记录头结点：它的next节点=最终要返回链表的头节点
        boolean flag = false;  //false:头节点没确定  true:头节点已确定
        ListNode pre = n;
        n.next = pHead;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (next.item == cur.item) {
                while (next != null && next.item == cur.item) { //针对1-> 1 -> 1 -> 2 这种连续的情况
                    next = next.next;
                }
                pre.next = next;  //删除重复节点
                cur = next;
            } else {
                if (!flag) {
                    n.next = cur;   //确定头结点
                    flag = true;
                }
                /*移动中……*/
                pre = cur;
                cur = next;
            }
        }
        return n.next;
    }


    /**
     * 方法二：利用递归
     */
    public ListNode deleteDuplication1(ListNode pHead) {
        if (pHead == null || pHead.next == null) { // 只有0个或1个结点，则返回
            return pHead;
        }
        if (pHead.item == pHead.next.item) { // 当前结点是重复结点
            ListNode pNode = pHead.next;
            while (pNode != null && pNode.item == pHead.item) {
                // 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
                pNode = pNode.next;
            }
            return deleteDuplication(pNode); // 从第一个与当前结点不同的结点开始递归
        } else { // 当前结点不是重复结点
            pHead.next = deleteDuplication(pHead.next); // 保留当前结点，从下一个结点开始递归
            return pHead;
        }
    }

    @Test
    public void test() {

        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("a");
        list.add("c");

        ListNode head = deleteDuplication1(list.getHead());
        while (head != null) {
            System.out.println(head.item);
            head = head.next;
        }


    }
}






