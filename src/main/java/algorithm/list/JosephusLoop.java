package algorithm.list;

/**
 * Created by golden on 2016/9/23 0023.
 */

/*
 *   约瑟夫环
 */
public class JosephusLoop {
    private static class Node {
        private int value;
        private Node next;

        public Node(int Value) {
            this.value = Value;
        }
    }

    private int sum;  //总人数
    private int number;  //出圈数字
    private Node head;

    public void kill(int sum, int number) {
        Node node = null;
        //初始化循环列表
        for (int i = 1; i <= sum; i++) {
            Node temp = new Node(i);
            if (node == null) {  //只有一个节点时；
                node = temp;
                node.next = node;
                head = node;
            } else {
                node.next = temp;
            }
            node = node.next;
        }
        node.next = head;  //最终让其首尾相连，形成循环链表

        //执行出圈操作
        System.out.println("出圈顺序为:");
        while (node != node.next) {    //注意：node是从头结点的前一个节点开始遍历的；
            for (int i = 1; i < number; i++) {  //遍历到出圈标号的前一个标号
                node = node.next;
            }
            //出循环之后，就是要删除的节点，删除第m个结点
            System.out.print(node.next.value + " "); //出圈的数字
            node.next = node.next.next;  //注意：此时并没有破坏原有的环结构，只是用一个临时的变量遍历时跳过而已；
        }
        System.out.println("\n幸运者是:" + node.value);//原来是10号
    }


    public static void main(String[] args) {
        JosephusLoop jl = new JosephusLoop();
        jl.kill(17, 3);
    }

}
