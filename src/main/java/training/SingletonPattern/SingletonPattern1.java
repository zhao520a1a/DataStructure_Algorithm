package training.SingletonPattern;

/**
 * 饿汉式 : 在类加载时就创建单例，因此不存在线程安全问题；但会浪费内存资源
 *
 * 单例模式的套路：
 * 1. 构造器私有化
 * 2. 搞一个获取单利对象的入口
 * @Description
 * @author: Golden
 */

public class SingletonPattern1 {

    private static SingletonPattern1 singletonPattern = new SingletonPattern1();


    private SingletonPattern1() {

    }

    public static SingletonPattern1 getInstance() {
        return singletonPattern;
    }



}
