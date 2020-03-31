package algorithm.stack;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;



/**
 * @描述：实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * @思路：
 * @复杂度：  时间复杂度O(1)   空间复杂度O(n)
 * @链接： https://www.nowcoder.com/practice/05e57ce2cd8e4a1eae8c3b0a7e9886be?tpId=101&tqId=33073&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */

public class GetMinStack {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    //入栈
    public Integer push(Integer item) {
        dataStack.push(item);
        if (minStack.empty()) {
            minStack.push(item);
        } else {
            Integer top = minStack.peek();
            if (top > item) {
                //总是存储最小值
                minStack.push(item);
            }
        }
        return item;
    }

    //出栈
    public Integer pop() {
        Integer item = dataStack.pop();
        if (!minStack.isEmpty()) {
            Integer top = minStack.peek();
            if (item.equals(top)) {
                minStack.pop();
            }
        }
        return item;

    }


    //取栈顶
    public Integer peek() {
        return dataStack.peek();
    }


    //获取最小值
    public Integer getMin() {
        return minStack.isEmpty() ? null : minStack.peek();
    }


    @Test
    public void testDemo() {
        GetMinStack stack = new GetMinStack();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }


    public static void main(String[] args) throws IOException {
        GetMinStack stack = new GetMinStack();

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(sc.readLine());
        for (int i = 0; i < row; i++) {
            String[] str = sc.readLine().split(" ");
            if ("push".equals(str[0])) {
                stack.push(Integer.valueOf(str[1]));
                continue;
            }
            if ("pop".equals(str[0])) {
                stack.pop();
                continue;
            }
            System.out.println(stack.getMin());
        }
    }


}
