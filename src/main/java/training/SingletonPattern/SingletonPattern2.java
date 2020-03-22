package training.SingletonPattern;

/**
 * 懒汉式：在类加载时并不会立马创建单例对象，而是只生成一个单例的引用，即可以延时加载。
 * 但下面的实现方式，并发情况下不安全
 *
 * 单例模式的套路：
 * 1. 构造器私有化
 * 2. 搞一个获取单利对象的入口
 * @Description
 * @author: Golden
 */

public class SingletonPattern2 {

    private static SingletonPattern2 singletonPattern;


    private SingletonPattern2() {

    }

    public static SingletonPattern2 getInstance() {
        if(singletonPattern == null) {
            singletonPattern = new SingletonPattern2();
        }
        return singletonPattern;
    }




}
