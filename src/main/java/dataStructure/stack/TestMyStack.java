package dataStructure.stack;

/**
 * Created by golden on 2016/9/19 0019.
 */
public class TestMyStack {

    public static void main(String[] args) {
       // com.xin.stack.MyStack stack = new com.xin.stack.MyArrayStack();
       MyStack stack = new MyLinkedStack();
        stack.push(1) ;
        stack.push(2) ;
        stack.push(3) ;
        //stack.pop() ;
      // System.out.println(stack.peek());
        stack.toString();
       System.out.println(stack.toString());
    }
}
