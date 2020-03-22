package training.SingletonPattern;

/**
 * DCL懒汉式（双重检测锁模式）：由于JVM底层内部模型的原因，偶尔会出现问题，因此不建议使用；并且利用反射机制还是可以搞事情的
 *
 * 单例模式的套路：
 * 1. 构造器私有化
 * 2. 搞一个获取单利对象的入口
 * @Description
 * @author: Golden
 */

public class SingletonPattern3 {

    private volatile static SingletonPattern3 singletonPattern;  // 防止指令重排


    private SingletonPattern3() {

    }

    //双重检测
    public static SingletonPattern3 getInstance() {
        if(singletonPattern == null) {
            synchronized (SingletonPattern3.class) {
                if(singletonPattern == null) {
                    singletonPattern = new SingletonPattern3();
                }
            }
        }
        return singletonPattern;
    }




}
