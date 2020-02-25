package dataStructure.stack;

/**
 * Created by golden on 2016/9/19 0019.
 */
public interface MyStack<T> {

    boolean isEmpty();

    void clear();

    int size();

    boolean push(T data);

    T pop();

    T peek();

}
