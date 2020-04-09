package algorithm.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述：单链表的选择排序
 * @思路： 1. 在未排序的部分中找到最小值节点
 * 2. 删除该节点（删除需要找到该节点的前一个节点，因此步骤1更改为找到最小值节点前一个节点），并将该节点连接到排好序的链表尾部
 * @复杂度： 时间O(N ^ 2)  空间O(1)
 * @链接：https://www.nowcoder.com/practice/78f83c3f12d2464591ebc5a73183db35
 */
public class SelectionSort {

    public static Node selectionSort(Node head) {
        Node tail = null; //有序部分的尾节点
        Node curr = head; //未排序的头节点
        Node preSmall = null; //最小节点前一个节点
        Node small = null; //最小节点

        while (curr != null) {
            small = curr; //初始化最小值

            //--在未排序的部分中找到最小值节点前一个节点
            preSmall = getSmallestPreNode(curr);

            //--删除该节点
            if (preSmall != null) {
                small = preSmall.getNext();
                preSmall.setNext(small.getNext());
            }

            //--将该节点连接到排好序的链表尾部
            if (tail == null) {
                head = small; // 整个链表的最小值为头节点
            } else {
                tail.setNext(small);
            }
            tail = small;

            curr = curr == small ? curr.getNext() : curr; // 未排序的头节点是否移动
        }

        return head;
    }

    private static Node getSmallestPreNode(Node head) {
        Node preSmall = null;
        Node small = head;
        Node preCurr = head;
        Node curr = head.getNext();
        while (curr != null) {
            if (curr.getValue() < small.getValue()) {
                preSmall = preCurr;
                small = curr;
            }
            //移动
            preCurr = curr;
            curr = curr.getNext();
        }
        return preSmall;
    }


    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{6, 4, 3, 2, 5});
        head = SelectionSort.selectionSort(head);
        Node.printNodeList(head);
    }

}


class Main8 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String n = in.readLine();
        String[] items = in.readLine().split(" ");
        Node head = Node.createNodeList(items);
        head = SelectionSort.selectionSort(head);
        Node.printNodeList(head);
    }


}
