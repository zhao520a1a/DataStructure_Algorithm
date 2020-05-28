package training;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @描述：
 * @思路：
 * @复杂度：
 * @链接：
 */
public class Jdk8ApiDemo {


    public static void main(String[] args) {

//      --- list中内容转化为逗号分隔的字符串
//        JDK8：
        List<String> symbolList = Lists.newArrayList("清平乐", "猎狐", "王牌对王牌", "笑起来真好看", "斗罗大陆", "风味人间");

        System.out.println(String.join(",", symbolList));
//        第三方Jar:
        System.out.println(Joiner.on(",").join(symbolList));
//        或
        System.out.println(StringUtils.join(symbolList.toArray(), ","));


        //      --- 集中删除元素
        List<String> itemList = Lists.newArrayList("a", "b", "c", "d", "e", "f");
        //使用removeIf方法，->里的是判断条件，如果符合这个条件就删除。这里会删除带有c的元素
        itemList.removeIf(s -> s.contains("c"));
        itemList.forEach(System.out::println);

//      --- 类型转换
//        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));


//        -- 数组如何转成List集合
        String[] array = {"李雷", "韩梅梅", "暴徒张三", "玉田"};
//        1.使用List.of()  此方法为 Java9新增方法，定义在List接口内，并且为静态方法，故可以由类名直接调用。
//        List<String> resultList = List.of(array);
//        2.使用Arrays.asList()
//        注意：调用Arrays.asList()时，其返回值类型是ArrayList，但此ArrayList是Array的内部类，调用add()时，会报错：java.lang.UnsupportedOperationException，并且结果会因为array的某个值的改变而改变，故需要再次构造一个新的ArrayList。
        List<String> resultList1 = new ArrayList<>(Arrays.asList(array));
//        3.使用Collections.addAll()
        List<String> resultList2 = new ArrayList<>(array.length);
        Collections.addAll(resultList2, array);


    }


}
