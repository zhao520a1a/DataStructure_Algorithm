package training.SingletonPattern;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/22
 */

public class SingletonPatternTest {

    public static void main(String[] args) {
        SingletonPattern1 a1 = SingletonPattern1.getInstance();
        SingletonPattern1 a2 = SingletonPattern1.getInstance();
        System.out.println(a1 == a2);

        SingletonPattern2 b1 = SingletonPattern2.getInstance();
        SingletonPattern2 b2 = SingletonPattern2.getInstance();
        System.out.println(b1 == b2);

        SingletonPattern3 c1 = SingletonPattern3.getInstance();
        SingletonPattern3 c2 = SingletonPattern3.getInstance();
        System.out.println(c1 == c2);

        SingletonPattern4 d1 = SingletonPattern4.getInstance();
        SingletonPattern4 d2 = SingletonPattern4.getInstance();
        System.out.println(d1 == d2);

        SingletonPattern5 e1 = SingletonPattern5.getInstance();
        SingletonPattern5 e2 = SingletonPattern5.getInstance();
        System.out.println(e1 == e2);

    }

}
