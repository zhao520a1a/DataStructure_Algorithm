package algorithm.list;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @描述：打印两个升序链表的公共部分
 * @思路：
 * @复杂度：时间复杂度 O(N) (N为两个链表中较长链表的长度)
 * @链接：https://www.nowcoder.com/practice/8943eea40dbb4185b187d80fd050fee9
 */
class PrintCommonPart {

    public static void printCommonPart(Node head1, Node head2) {
        StringBuilder builder = new StringBuilder();
        Node node1 = head1;
        Node node2 = head2;
        while (node1 != null && node2 != null) {
            if (node1.getValue() == node2.getValue()) {
                builder.append(node1.getValue()).append(" ");
                node1 = node1.getNext();
                node2 = node2.getNext();
            } else if (node1.getValue() < node2.getValue()) {
                node1 = node1.getNext();
            } else {
                node2 = node2.getNext();
            }
        }
        System.out.print(builder.toString());
    }


    public static void main(String[] args) {
        Integer[] arr1 = {1, 3, 4, 5, 6};
        Integer[] arr2 = {1, 2, 4, 7, 8};
        Node head1 = Node.createNodeList(arr1);
        Node head2 = Node.createNodeList(arr2);
        PrintCommonPart.printCommonPart(head1, head2);
    }


}


class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        String[] strings1 = input.readLine().split(" ");
        Node head1 = Node.createNodeList(strings1);
        int m = Integer.parseInt(input.readLine());
        String[] strings2 = input.readLine().split(" ");
        Node head2 = Node.createNodeList(strings2);
        PrintCommonPart.printCommonPart(head1, head2);
    }


}
