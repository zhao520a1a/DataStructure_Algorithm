package algorithm.list;


import dataStructure.list.node.ListNode;

/**
 * 返回链表中倒数第k个节点，要求：时间O(n),空间(1)
 * 概要：遍历一遍，k-- （k变为k-n）  ————> 遍历到k==0结束，位置为 n-k（第k个节点的前节点）
 * Created by golden on 2017/4/21 0021.
 */
public class Find_KthToTail {
    public static ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode cur = head;
        while (cur != null) {
            k--;
            cur = cur.next;
        }
        /*经过全部遍历后k的取值变为k-N*/
        System.out.println(k);
        if (k > 0) {
            return null;
        }
        if (k == 0) {
            return head;
        }

        if (k < 0) {
            cur = head;
            while (++k != 0) {            /*遍历后k的取值变为N-k(即：指针指向了所求结点的前一个结点)*/
                cur = cur.next;
            }
            return cur.next;
        }
        return null;
    }




}
