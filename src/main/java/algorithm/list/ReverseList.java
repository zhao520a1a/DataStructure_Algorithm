package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述： 反转单向、双向链表
 * @思路： 涉及三个节点， preNode、currode、nextNode
 * 1.声明preNode nextNode；
 * 2.反转preNode、currNode顺序 -- 重点
 * 3.移动指针，更新preNode、currNode
 * @复杂度：时间O(N)
 * @链接：https://www.nowcoder.com/practice/b66a251dec8847f386bbe6cd96b7e9c8?tpId=101&tqId=33175&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
class ReverseList {

    public static Node reverseList(Node head) {
        Node node = head;
        Node pre = null;  //初始化preNode
        Node next = null;
        while (node != null) {
            next = node.getNext(); //初始化nextNode

            //反转顺序
            node.setNext(pre);

            //移动
            pre = node; //pre 当前节点
            node = next;
        }
        // 此时，pre就是反转链表的第一个节点
        return pre;
    }


    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode node = head;
        DoubleNode pre = null;  //初始化preDoubleNode
        DoubleNode next = null;
        while (node != null) {
            next = node.getNext(); //初始化nextDoubleNode
            //反转顺序
            node.setNext(pre);
            node.setLast(next);

            //移动
            pre = node; //pre 当前节点
            node = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        head = ReverseList.reverseList(head);
        Node.printNodeList(head);

        DoubleNode doubleHead = DoubleNode.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        doubleHead = ReverseList.reverseDoubleList(doubleHead);
        DoubleNode.printNodeList1(doubleHead);
    }


}


class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        String[] strings1 = input.readLine().split(" ");
        Node head1 = Node.createNodeList(strings1);
        head1 = ReverseList.reverseList(head1);


        int m = Integer.parseInt(input.readLine());
        String[] strings2 = input.readLine().split(" ");
        DoubleNode head2 = DoubleNode.createNodeList(strings2);
        head2 = ReverseList.reverseDoubleList(head2);

        Node.printNodeList(head1);
        DoubleNode.printNodeList1(head2);

    }


}



