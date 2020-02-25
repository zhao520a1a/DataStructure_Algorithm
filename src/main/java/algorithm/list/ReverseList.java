package algorithm.list;


import dataStructure.list.node.ListNode;

/**
 *
 * 反转链表； 时间O(n) 空间O(1)
 * 要求：返回反转后链表新的头结点
 * Created by golden on 2017/4/26 0026.
 */
public class ReverseList {

    public ListNode reverseList(ListNode node){
        ListNode pre = null;
        ListNode next = null;

        while(node != null){
            next = node.next;//记录下一个结点
            node.next = pre;  //反转指向顺序
            /*若是反转双向链表，只需要加一句： node.pre = next; */
            pre = node;  //移动
            node = next;  //移动
        }

        return pre;
    }
}
