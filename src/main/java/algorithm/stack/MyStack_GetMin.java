package algorithm.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 实现一个具有能返回栈中最小元素的操作的特殊栈；时间O(1),空间O(n)
 * 思想:使用两个栈，一个栈用来保存当前栈中的元素（就是一个正常的栈）记为：stackData；
 * 一个用于保存每一步的最小值，记为：stackMin；
 * 数据压入规则：
 * 正常压入stackData中；
 * stackMin为空，直接压入；非空，同stackMin栈顶比较，小于则压入；否则不需要压入；
 * <p>
 * 数据弹出规则：
 * 弹出stackData栈顶元素value
 * 将value同stackMin中的栈顶比较，若等于，则stackMin也要弹出栈顶元素；否则不需要弹出；
 * 得到整个栈结构的最小值：
 * 取stackMin的栈顶元素值
 * <p>
 * Created by golden on 2017/4/24 0024.
 */
public class MyStack_GetMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;


    public MyStack_GetMin() {
        stackData = new Stack();
        stackMin = new Stack();
    }

    public void push(int newNum) {
        stackData.push(newNum);

        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
        } else if (this.getMin() > newNum) {
            stackMin.push(newNum);
        }
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = stackData.pop();
        if (value == getMin()) {
            stackMin.pop();
        }
        return value;
    }

    public int peek() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stackData.peek();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stackMin.peek();
    }


    @Test
    public void test(String[] args) {
        MyStack_GetMin ms = new MyStack_GetMin();
        int[] a = {5, 3, 6, 1, 0, 2, 7, 9, 8};
        for (int i : a) {
            ms.push(i);
        }
        System.out.println("栈中的最小元素为:" + ms.getMin());
        for (int i = 0; i < a.length; i++) {
            ms.pop();
        }
    }
}
