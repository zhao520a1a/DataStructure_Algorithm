package algorithm.queue;

import org.junit.Test;

import java.util.Stack;

/**
 * 问题：用两个Stack来实现一个Queue;
 * 思想：栈的特点是先进后出；而队列的特点是先进先出，用两个栈正好能把顺序调过来；
 * 一个栈，作为压入栈，在压入数据时，只往这个栈里压入,记作：stackPush；
 * 一个栈 ，作为弹出栈，在弹出数据时，只从这个栈里弹出,记作：stackPop；
 * 必须满足的条件：
 * 条件一：只有在stackMin为空时，才能将stackData倒入stackMin中；
 * 条件二：在倒入时，必须一次将stackData所有元素全部倒入stackMin中；
 *
 */
public class TwoStackQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStackQueue() {
        this.stackPush = new Stack();
        this.stackPop = new Stack();
    }

    public void add(int newNum ) {
        stackPush.push(newNum);
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("queue is empty!");
        } else if (stackPop.empty()) {  //满足条件1
            while (!stackPush.empty()) {  //满足条件2
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("queue is empty!");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();

    }

    @Test
    public void test() {
        TwoStackQueue tsq = new TwoStackQueue();
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        for (int i : a) {
            tsq.add(i);
        }

        for (int j = 0; j < a.length; j++) {
            int num = tsq.peek();
            tsq.poll();
            System.out.println(num);
        }
    }

}
