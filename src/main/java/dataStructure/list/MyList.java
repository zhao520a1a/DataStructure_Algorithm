package dataStructure.list;

/**
 * Created by golden on 2016/9/21 0021.
 */
public interface MyList<T> {

    boolean add(T element);
    boolean add(int index, T element);

    T remove(int index);

    T getValueByIndex(int index);

    int size();

    void clear();

    Object[] toArray();

}
