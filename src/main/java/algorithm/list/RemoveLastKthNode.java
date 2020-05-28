package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @描述：在链表中删除倒数第K个节点
 * @思路：链表长度为N，要删除倒数第K个节点；最重要就是定位到它的前一个节点，即：第N-K个节点； 过程分两步：
 * 1. 先从头到位遍历一遍遍历表，每遍历一个节点将K值减1, 将K值更新为K-N；
 * 2. 再遍历一遍链表，每遍历一个节点将K值加1，直到K值为0停止，这样就将K值更新为0-(K-N）= N-K，此时的节点便是第N-k个节点，即：要删除"倒数第k个节点"的前一个节点。
 * @复杂度： 时间O(n) 空间O(1)
 * @链接：https://www.nowcoder.com/questionTerminal/e5d90aac4c8b4628aa70d9b6597c0560
 */
class RemoveLastKthNode {


    public static Node removeLastKthNode(Node head, int k) {
        Node cur = head;
        while (cur != null) {
            cur = cur.getNext();
            k--;
        }
        if (k > 0) { // 说明不存在倒数第k个节点
            System.out.println("说明不存在倒数第k个节点");
            return head;
        } else if (k == 0) { //说明头节点就是倒数第k个节点
            return head.getNext();
        } else {
            cur = head;
            while (++k != 0) {
                cur = cur.getNext();
            }
            cur.setNext(cur.getNext().getNext());
            return head;
        }
    }


    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        head = RemoveLastKthNode.removeLastKthNode(head, 2);
        Node.printNodeList(head);
    }


}


class Main1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = input.readLine().split(" ");
        String[] s2 = input.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int k = Integer.parseInt(s1[1]);
        Node head = Node.createNodeList(s2);
        head = RemoveLastKthNode.removeLastKthNode(head, k);
        Node.printNodeList(head);
    }

}


