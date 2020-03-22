package training.SingletonPattern;

/**
 * 静态内部类式：使用静态内部类解决了线程安全问题，并实现了延时加载
 * <p>
 * 单例模式的套路：
 * 1. 构造器私有化
 * 2. 搞一个获取单利对象的入口
 *
 * @Description
 * @author: Golden
 */

public class SingletonPattern4 {

    private static class MyInterClass {
        private static SingletonPattern4 singletonPattern = new SingletonPattern4();  // 防止指令重排

    }


    private SingletonPattern4() {
    }

    public static SingletonPattern4 getInstance() {
        return MyInterClass.singletonPattern;
    }


}
