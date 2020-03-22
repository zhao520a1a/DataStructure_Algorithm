package training.SingletonPattern;

/**
 * 枚举方式实现单例模式
 *
 * @Description
 * @author: Golden
 */

public enum  SingletonPattern5 {
    INSTANCE;

    public static SingletonPattern5 getInstance(){
        return INSTANCE;
    }


}
