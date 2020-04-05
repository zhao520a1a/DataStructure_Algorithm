package algorithm.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述：合并两个有序的单链表
 * @思路： 1. 确定头节点
 * 2. 合并
 * 2.1 声明每次比较两个链表需要用到各自其上的一个节点；
 * 2.2 声明链接时，pre：上次比较的结果节点 next：下次比较需要的节点
 * 2.3 按比较后，指针是否在当前链表继续移动，分为两种情况；
 * 2.4 两条链表比较完毕后，将其余没参与比较的节点关联到结果链表中。
 * @复杂度：时间O(M+N)【M、N分别是两个链表的长度】， 空间(1)
 * @链接：https://www.nowcoder.com/questionTerminal/98a51a92836e4861be1803aaa9037440?answerType=1&f=discussion
 */
class MergeList {

    public static Node merge(Node head1, Node head2) {
        // -- 校验
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // -- 确定头节点
        Node head = head1.getValue() < head2.getValue() ? head1 : head2;

        // -- 合并
        //cur1、cur2为每次比较遍历到的两个节点
        Node cur1 = head == head1 ? head1 : head2;   //一条链表的节点
        Node cur2 = head == head1 ? head2 : head1; //另一条链表的节点
        Node pre = null;  //上次比较较小的节点
        Node next = null;

        while (cur1 != null && cur2 != null) {
            if (cur1.getValue() <= cur2.getValue()) { // 比较后，指针在当前链表继续移动即可
                pre = cur1;
                cur1 = cur1.getNext();
            } else { //比较后，指针需要关联另一条链表中的节点
                next = cur2.getNext();

                //关联另一条链表中的节点
                pre.setNext(cur2);
                cur2.setNext(cur1);

                pre = cur2;
                cur2 = next;
            }
        }
        //直到将一条链表完全遍历完，结束循环后；将另一条上的节点关联到最终的结果链表中。
        Node lastNode = cur1 == null ? cur2 : cur1;
        pre.setNext(lastNode);

        return head;
    }


    public static void main(String[] args) {
        Integer[] arr1 = {1, 3, 4, 5, 6};
        Integer[] arr2 = {1, 2, 4, 7, 8};
        Node head1 = Node.createNodeList(arr1);
        Node head2 = Node.createNodeList(arr2);
        Node head = MergeList.merge(head1, head2);
        Node.printNodeList(head);
    }


}


class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String n = input.readLine();
        String[] strings1 = input.readLine().split(" ");
        Node head1 = Node.createNodeList(strings1);
        String m = input.readLine();
        String[] strings2 = input.readLine().split(" ");
        Node head2 = Node.createNodeList(strings2);
        Node head = MergeList.merge(head1, head2);
        Node.printNodeList(head);
    }


}

