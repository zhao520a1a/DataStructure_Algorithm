package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述：
 * @思路：
 * @复杂度： 时间O(n) 空间O(1)
 * @链接：
 */

/**
 * @描述：删除链表中第K个节点
 * @思路：链表长度为N，要删除第K个节点；最重要就是定位到它的前一个节点
 * @复杂度： 时间O(n) 空间O(1)
 * @链接：https://www.nowcoder.com/practice/0796dbf0eb054716937b0b82e0671c5f?tpId=101&tqId=33174&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
class RemoveKthNode {


    public static Node removeKthNode(Node head, int k) {

        if (k <= 0) { // 说明不存在倒数第k个节点
            System.out.println("说明不存在第k个节点");
            return head;
        } else if (k == 1) { //说明头节点就是倒数第k个节点
            return head.getNext();
        } else {
            Node cur = head;
            while (--k != 1) {
                cur = cur.getNext();
            }
            cur.setNext(cur.getNext().getNext());
            return head;
        }
    }


    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        head = RemoveKthNode.removeKthNode(head, 2);
        Node.printNodeList(head);
    }


}


class Main5 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = input.readLine().split(" ");
        String[] s2 = input.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int k = Integer.parseInt(s1[1]);
        Node head = Node.createNodeList(s2);
        head = RemoveKthNode.removeKthNode(head, k);
        Node.printNodeList(head);
    }

}


