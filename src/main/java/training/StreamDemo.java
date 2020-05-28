package training;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 最靓的崽： `链式编程 + 流式计算 + lambda表达式`
 * 一下数据，进行操作筛选用户：
 * 要求：一行代码做出此题，时长1分钟！
 * 1、全部满足偶数ID
 * 2、年龄都大于24
 * 3、用户名转为大写
 * 4、用户名字母倒排序
 * 5、只能输出一个名字
 *
 * @Description
 * @author: Golden
 */

public class StreamDemo {

    public static void main(String[] args) {


        //流式计算
        List<User> list = Lists.newArrayList();
        list.add(User.builder().id(1L).name("Tomcat").age(21).build());
        list.add(User.builder().id(2L).name("Java").age(22).build());
        list.add(User.builder().id(3L).name("Double").age(23).build());
        list.add(User.builder().id(4L).name("Php").age(24).build());
        list.add(User.builder().id(5L).name("Aim").age(25).build());
        list.add(User.builder().id(6L).name("Ruby").age(26).build());
        list.add(User.builder().id(7L).name("Python").age(27).build());

        list.stream().filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 24)
                .map(user -> user.getName().toUpperCase())
                .sorted((String::compareTo))
                .limit(1).forEach(System.out::println);

    }


}
