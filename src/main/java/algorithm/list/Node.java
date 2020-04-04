package algorithm.list;


class Node {

    private Node next;

    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public static Node createNodeList(Integer[] values) {
        Node head = new Node((values[0]));
        Node node = head;
        for (int i = 1; i < values.length; i++) {
            Node newNode = new Node(values[i]);
            node.next = newNode;
            node = newNode;
        }
        return head;
    }

    public static Node createNodeList(String[] values) {
        Node head = new Node(Integer.parseInt(values[0]));
        Node node = head;
        for (int i = 1; i < values.length; i++) {
            Node newNode = new Node(Integer.parseInt(values[i]));
            node.next = newNode;
            node = newNode;
        }
        return head;
    }


    public static void printNodeList(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.getValue()).append(" ");
            head = head.getNext();
        }
        System.out.println(sb.toString());
    }


}
