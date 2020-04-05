package algorithm.list;


class DoubleNode {

    private DoubleNode last;

    private DoubleNode next;

    private int value;

    public DoubleNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getLast() {
        return last;
    }

    public void setLast(DoubleNode last) {
        this.last = last;
    }


    public static DoubleNode createNodeList(String[] values) {
        DoubleNode head = new DoubleNode(Integer.parseInt(values[0]));
        DoubleNode node = head;
        DoubleNode pre = null;
        for (int i = 1; i < values.length; i++) {
            DoubleNode newNode = new DoubleNode(Integer.parseInt(values[i]));
            node.next = newNode;
            node.last = pre;
            pre = node;
            node = newNode;
        }
        return head;
    }


    public static DoubleNode createNodeList(Integer[] values) {
        DoubleNode head = new DoubleNode((values[0]));
        DoubleNode node = head;
        DoubleNode pre = null;
        for (int i = 1; i < values.length; i++) {
            DoubleNode newNode = new DoubleNode(values[i]);
            node.next = newNode;
            node.last = pre;
            pre = node;
            node = newNode;
        }
        return head;
    }


    public static void printNodeList1(DoubleNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.getValue()).append(" ");
            head = head.getNext();
        }
        System.out.println(sb.toString());
    }


    public static void printNodeList2(DoubleNode last) {
        StringBuilder sb = new StringBuilder();
        while (last != null) {
            sb.append(last.getValue()).append(" ");
            last = last.getLast();
        }
        System.out.println(sb.toString());
    }


}
