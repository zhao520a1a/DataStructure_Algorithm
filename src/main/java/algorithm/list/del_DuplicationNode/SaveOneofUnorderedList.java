package algorithm.list.del_DuplicationNode;

import dataStructure.list.MyLinkedList;
import dataStructure.list.node.ListNode;
import org.junit.Test;

import java.util.HashSet;

/**
 * 删除无序单链表中值重复出现的节点;重复的结点保留一个
 * 例：1->2->3->2->4->1->1->null
 *     1->2->3->4->null
 * <p>
 * Created by golden on 2017/5/6 0006.
 */
public class SaveOneofUnorderedList {

    /**
     * 方法一：利用哈希表；   时间：O(n), 空间：O(n)
     */
    public void removeRep1(ListNode head) {
        if (head == null) {
            return;
        }

        HashSet set = new HashSet();
        set.add(head.item);  //头节点不可能重复，直接放入；

        ListNode pre = head;
        ListNode cur = head.next;   //从第二节点检查
        while (cur != null) {
            if (set.contains(cur.item)) {
                pre.next = cur.next;
            } else {
                set.add(cur.item);
                pre = cur;
            }
            cur = cur.next;
        }

    }


    /**
     * 方法二：利用类似选择排序的过程；   时间：O(n^2), 空间：O(1)
     * 从头到尾遍历，
     * 先选择一个节点，往后遍历检查，发现就删除；
     * 检查到链表结尾后，再选择它的下一节点，重复操作，直到已经将整个链表都遍历了一遍；
     */
    public void removeRep2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            pre = cur;
            next = cur.next;

            while (next != null) {
                if (cur.item.equals(next.item)) {
                    pre.next = next.next; //删除重复节点；
                } else {
                    pre = next;
                }
                next = next.next;
            }

            cur = cur.next;
        }
    }


    @Test
    public void test() {

        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("a");
        list.add("c");

        removeRep1(list.getHead());
        removeRep2(list.getHead());
        Object[] objs = list.toArray();
        for (int i = 0; i < objs.length; i++) {
            if(objs[i] != null){
                System.out.println(objs[i] + " ");
            }
        }

    }
}






