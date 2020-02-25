package dataStructure.stack;

/**
 * Created by golden on 2016/9/19 0019.
 */
public class MyArrayStack<T> implements MyStack {

    private Object[] objs = new Object[15];
    private int size = 0;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for(int i=0; i<size; i++) {
            objs[i] = null;   // 将数组中的数据置为null, 方便GC进行回收
        }
        size = 0;
    }

    @Override
    public  int size() {
        return size;
    }

    @Override
    public boolean push(Object data) {
        if(size >= objs.length) {     // 判断是否需要进行数组扩容
            reSize();
        }
        objs[size++]  = data;    //注意：在插入数据的同时也改变了size的值
        return true;
    }

    /*
    *  数组扩容
    */
    private void reSize() {
        Object[] temp = new Object[objs.length * 3 / 2 + 1];  //算法随意
        for(int i=0 ; i<size; i++) {
            temp[i] = objs[i];
            objs[i] = null;
        }
        objs = temp;
    }

    @Override
    public T pop() {
        if(size == 0) {
            return null;
        }
        Object oldValue  = objs[size - 1];
        objs[--size] = null;  //释放栈顶元素
        return (T)oldValue;
    }

    @Override
    public T peek() {
        if(size == 0) {
            return null;
        }
        return (T) objs[size - 1];
    }

    @Override
    public String toString() {
         StringBuilder  sb = new StringBuilder();
         for(int i=0; i<size; i++) {
             sb.append(objs[i].toString() + " ");
         }
        return sb.toString();
    }
}
