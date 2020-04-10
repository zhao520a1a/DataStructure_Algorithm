package algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/23
 */

public class InputUtils {


    /**
     * 输入多行文本
     * 输入格式
     * 6
     * push 3
     * push 2
     * push 1
     * getMin
     * pop
     * getMin
     */
    public void readMoreLine() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(in.readLine());
        while(row-- >0){
            String[] str = in.readLine().split(" ");

            if ("push".equals(str[0])) {
                continue;
            }
            if ("pop".equals(str[0])) {
                continue;
            }
        }
    }


    /**
     * 输入两行文本
     * 输入格式
     * 5
     * 5 8 4 3 6
     */

    public void readTwoLine() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(in.readLine());
        String[] item = in.readLine().split(" ");
        for (int i = 0; i < item.length; i++) {

        }
    }


    public static void printStack(Stack<Integer> s){
        StringBuilder sb = new StringBuilder();
        while(!s.empty()){
            sb.append(s.pop()+" ");
        }
        System.out.println(sb.substring(0,sb.length()-1));
    }


//
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(input.readLine());
//        String[] strings1 = input.readLine().split(" ");
//        Node head1 = Node.createNodeList(strings1);
//        int m = Integer.parseInt(input.readLine());
//        String[] strings2 = input.readLine().split(" ");
//        Node head2 = Node.createNodeList(strings2);


}
