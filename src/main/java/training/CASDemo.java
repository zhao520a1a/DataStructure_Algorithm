package training;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS(compareAndSwap)： 比较并交换，它是一个CPU的并发原语，是一个原子性的内存级别的操作，本身就不会存在数据不一致的问题；
 * 它的功能就是判断内存中的某个位置，是否是预期值，如果是就更新为指定的值。
 *  Unsafe类是实现CAS的核心类，它可以操作特定内存中的数据（本质是调用native方法）
 * @Description
 * @author: Golden
 */

public class CASDemo {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        //实际是调用Unsafe类的compareAndSwapInt()
        atomicInteger.compareAndSet(5, 2020);
        System.out.println("==>" + atomicInteger.get());
        atomicInteger.compareAndSet(2020, 5);
        System.out.println("==>" + atomicInteger.get());




    }




}
