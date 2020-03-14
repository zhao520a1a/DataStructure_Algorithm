package training.threads;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description
 *
 * CopyOnWriteArrayList的写操作性能较差，而多线程的读操作性能较好。
 * Collections.synchronizedList写操作性能较好，而多线程的读操作性能较差（因为是采用了synchronized关键字的方式）。
 *
 * @author: Golden
 * @date: 2020/3/14
 */

public class ConcurrentCollections {

    public static void main(String[] args) {
        List list1 = Lists.newArrayList();  // 不支持并发操作
        List list2 = new Vector(); //效率低下
        List list3 = Collections.synchronizedList(list1); //工具类
        List list4 = new CopyOnWriteArrayList(); //并发类


        Set set1 = Collections.newSetFromMap(new ConcurrentHashMap(16));  //工具类
        Set set2 = new CopyOnWriteArraySet();//并发类

        Map map = new ConcurrentHashMap();//并发类

    }

}
