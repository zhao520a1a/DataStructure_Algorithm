package training;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @描述：Java集合类型转换
 * @思路： 1.自己编写转换（略）
 * 2.使用JDK工具类转换（要注意线上线下jdk版本兼容问题）
 * 3.使用第三方包转换 - 推荐 Google guava 工具类
 */
public class TypeConvertionDemo {

    public static void main(String[] args) {

        List<String> nameList = Lists.newArrayList("Answer", "AnswerAIL", "AI");
        List<User> userList = Lists.newArrayList();
        userList.add(User.builder().id(1L).name("C++").age(21).build());
        userList.add(User.builder().id(2L).name("Java").age(22).build());
        userList.add(User.builder().id(3L).name("C").age(22).build());
        userList.add(User.builder().id(4L).name("Php").age(24).build());
        userList.add(User.builder().id(5L).name("Go").age(25).build());
        userList.add(User.builder().id(6L).name("Ruby").age(26).build());
        userList.add(User.builder().id(7L).name("Python").age(27).build());

        /*
         * List -> Map
         */
        //将 List 转为 Map<String, 1>
        Map<String, Integer> map = nameList.stream().collect(Collectors.toMap(v -> v, v -> 1));
        System.out.println(map);

        //将 List 转为 Map<Long, String>
        Map<Long, String> idNameMap = userList.stream().collect(Collectors.toMap(User::getId, User::getName));
        System.out.println(idNameMap);

        //将 List 转为 Map<Long, T>
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(JSON.toJSONString(userMap));

        //将 List 转为 Map<Integer, T>  -- key重复
        //  注意：toMap 如果集合对象有重复的key，会报错Duplicate key ;可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2;
//        Map<Integer, User> ageUserMap = userList.stream().collect(Collectors.toMap(User::getAge, Function.identity()));
        Map<Integer, User> ageUserMap = userList.stream().collect(Collectors.toMap(User::getAge, Function.identity(), (k1, k2) -> k1));
        System.out.println(ageUserMap);

        //分组,List里面的对象元素，以某个属性来分组，例如，以age分组，将age相同的放在一起：
        Map<Integer, List<User>> groupBy = userList.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(JSON.toJSONString(userMap));

        //过滤出符合条件的数据
        List<User> filterList = userList.stream().filter(a -> "Java".equals(a.getName())).collect(Collectors.toList());
        System.out.println("过滤:" + filterList);

        //求和：将集合中的数据按照某个属性求和:
        int totalAge = userList.stream().mapToInt(User::getAge).sum();
        System.out.println("求和:" + totalAge);

        //最大值 最小值
        Optional<User> maxAgeUser = userList.stream().max(Comparator.comparing(User::getAge));
        maxAgeUser.ifPresent(System.out::println);
        Optional<User> minAgeUser = userList.stream().min(Comparator.comparing(User::getAge));
        minAgeUser.ifPresent(System.out::println);

        //去重: distinct()方法依赖hashCode()和equals()方法。 判断两个对象是否相同原理与HashMap定位key原理相同，先计算hashCode，如果hashCode相同继续调用equals()方法。
        List list = userList.parallelStream().distinct().collect(Collectors.toList());
        System.out.println("去重:" + list);
    }


}
