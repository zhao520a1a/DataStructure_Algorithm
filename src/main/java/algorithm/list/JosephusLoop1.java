package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述：环形链表的约瑟夫问题
 * @思路： 1. 找到最后一个节点
 * 2. 从最后一个节点开始遍历链表，满足条件后剔除节点并计数清零
 * @复杂度：时间O(N*M) N为链表长度、M是指定编号
 * @链接：https://www.nowcoder.com/practice/c3b34059faf546d3a7ee28f2b0154286
 */
class JosephusLoop1 {

    public static Node kill(Node head, int m) {
        if (head == null || head == head.getNext() || m < 1) {
            return head;
        }

        //找到最后一个节点
        Node last = head;
        while (last.getNext() != head) {
            last = last.getNext();
        }

        //从最后一个节点开始遍历链表，node代表当前节点，last代表当前节点的前一个节点
        int count = 0;
        Node node = head;
        while (node != last) {
            if (++count == m) {
                last.setNext(node.getNext());
                count = 0;
            } else {
                last = last.getNext();
            }
            node = last.getNext();
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = Node.createLoopNodeList(new Integer[]{1, 2, 3, 4, 5});
        head = JosephusLoop1.kill(head, 2);
        System.out.println(head.getValue());
        Node.printLoopNodeList(head);
    }

}


class Main6 {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] item = in.readLine().split(" ");
        int n = Integer.parseInt(item[0]);
        int m = Integer.parseInt(item[1]);

        Integer[] value = new Integer[n];
        for (int i = 0; i < value.length; i++) {
            value[i] = i + 1;
        }
        Node head = Node.createLoopNodeList(value);
        head = JosephusLoop1.kill(head, m);
        Node.printLoopNodeList(head);

    }


}