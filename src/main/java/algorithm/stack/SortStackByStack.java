package algorithm.stack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @描述：用一个栈实现另一个栈的排序
 * @思路：
 * @复杂度： 最好时间复杂度O(N)  最差平均复杂度O(N^2)
 * @链接：https://www.nowcoder.com/practice/ff8cba64e7894c5582deafa54cca8ff2?tpId=101&tqId=33081&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */

class SortStackByStack {


    /**
     * @param stack 无序的栈
     * @return 有序的栈（从栈顶到栈底，由大到小排序)
     */
    public static Stack<Integer> stackSort(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            while (!help.isEmpty() && help.peek() > curr) {
                stack.push(help.pop());
            }
            help.push(curr);
        }
        return help;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        Stack<Integer> result = SortStackByStack.stackSort(stack);
        while (!result.isEmpty()) {
            System.out.println(result.pop());
        }
    }

}

class Main {

    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(in.readLine());
        String[] item = in.readLine().split(" ");
        for (int i = 0; i < row; i++) {
            stack.push(Integer.parseInt(item[i]));
        }

        stack = SortStackByStack.stackSort(stack);

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }


}
