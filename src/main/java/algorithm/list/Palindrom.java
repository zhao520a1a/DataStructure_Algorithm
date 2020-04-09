package algorithm.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @描述：判断一个链表是否为回文结构
 * @思路： 1. 将链表的右半区入栈
 * 2.检查栈中的值和顺序是否与未入栈的一致
 * @复杂度：时间O(N)
 * @链接：https://www.nowcoder.com/practice/4b13dff86de64f84ac284e31067b86e2
 */
public class Palindrom {


    public static boolean isPalindrom(Node head) {
        //检验
        if (head == null || head.getNext() == null) {
            return false;
        }
        //--找到右半区的头结点
        Node right = head.getNext(); //从第二个结点开始
        Node last = head;
        while (last.getNext() != null && last.getNext().getNext() != null) {
            right = right.getNext();
            last = last.getNext().getNext();
        }
        //--将链表的右半区入栈
        Stack<Integer> stack = new Stack<>();
        while (right != null) {
            stack.push(right.getValue());
            right = right.getNext();
        }
        //--检查栈中的值和顺序是否与未入栈的一致
        Node node = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != node.getValue()) {
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = Node.createNodeList(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(Palindrom.isPalindrom(head));
    }


}

class Main7 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String n = in.readLine();
        String[] items = in.readLine().split(" ");
        Node head = Node.createNodeList(items);
        System.out.println(Palindrom.isPalindrom(head));

    }


}