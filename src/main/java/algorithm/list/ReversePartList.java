package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述： 反转部分单向链表
 * @思路： 1.找到from位置的前一个节点 和 找到to位置的后一个节点
 * 2.反转from到to位置的链表  -- 注意判断"换头问题"
 * 3.将部分链表同整体链表关联起来； --  注意判断"换头问题"
 * @复杂度：时间O(N) 空O(1)
 * @链接：https://www.nowcoder.com/practice/f11155006f154419b0bef6de8918aea2?tpId=101&tqId=33176&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
class ReversePartList {

    public static Node reversePartList(Node head, int from, int to) {
        //找到from位置的前一个节点 和 找到to位置的后一个节点
        Node node = head;
        Node fPre = null;
        Node tNext = null;
        int lenght = 0;
        while (node != null) {
            lenght++;
            fPre = lenght == from - 1 ? node : fPre;
            tNext = lenght == to + 1 ? node : tNext;
            node = node.getNext();
        }
        //校验输入数据的合法性
        if (from > to || from < 1 || to > lenght) {
            return head;
        }
        //反转from到to位置的链表，注意：要判断是否存在"换头问题"，即fPre是否为null。
        Node pre = fPre == null ? head : fPre.getNext();
        Node currNode = pre.getNext();
        pre.setNext(tNext); //目的：将反转后的链表，尾部同整个链表关联起来
        Node next = null;
        while (currNode != tNext) {
            next = currNode.getNext();
            currNode.setNext(pre);
            pre = currNode;
            currNode = next;
        }

        //不存在"换头问题"
        if (fPre != null) {
            fPre.setNext(pre); // pre就是反转链表的第一个节点，目的：将反转后的链表，头部同整个链表关联起来
            return head;
        }
        //存在"换头问题"
        return pre;
    }


    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        head = ReversePartList.reversePartList(head, 2, 3);
        Node.printNodeList(head);

    }


}


class Main4 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        String[] strings1 = input.readLine().split(" ");
        Node head1 = Node.createNodeList(strings1);
        String[] fromAndTo = input.readLine().split(" ");
        int from = Integer.parseInt(fromAndTo[0]);
        int to = Integer.parseInt(fromAndTo[1]);
        head1 = ReversePartList.reversePartList(head1, from, to);

        Node.printNodeList(head1);
    }


}



