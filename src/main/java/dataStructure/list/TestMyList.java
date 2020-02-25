package dataStructure.list;


public class TestMyList {

    public static void main(String[] args) {

       /* MyLinkedList<string> list = new  MyLinkedList<string>();
        list.add("b");
        list.add("c");
        list.add(0,"a");
        list.add(3,"d");
        list.add(2,"x");
        list.remove(0);
        list.remove(3);
        list.remove(2);

        System.out.println("\n" + Find_KthToTail.Find_KthToTail(list.getHead(), 10).item);

        */


        MyDeList<String> list = new MyDeList();
        list.addFirst("b");
        list.addLast("c");
        list.add(0, "a");
        list.add(3, "d");
        list.remove(0);
        list.removeFirst();
        list.removeLast();
        System.out.println(list.getValueByIndex(0));

        //list.clear();

        Object[] objs = list.toArray();
        for (Object o : objs) {
            System.out.print(  o + "  ");
        }


    }

}
