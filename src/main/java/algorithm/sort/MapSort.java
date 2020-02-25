package algorithm.sort;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;



/**
 * 这里主要是针对HashMap和HashTable进行排序；
 * 分为两类：对key排序； 	对value排序；
 * 对key排序： 可以借助于Arrays.sort()进行排序;
 * 对value排序：可以使用自身的entrySet()方法转换为List集合，然后用Collections.sort()进行排序，但必须自己实现排序排序规则，即要实现Comparator接口；
 * @author 小鑫哦
 *
 */
public class MapSort {
	Map<String, Integer> map = new HashMap<String, Integer>();
	@Test
	public void test() {
		map.put("我", 100);
		map.put("你", 200);
		map.put("他", 300);
		map.put("我们", 400);
		map.put("你们", 500);
		map.put("他们", 600);
		
		valueSort();
	}

	//对HashMap中的值进行排序；
	private void valueSort() {
		/*
		 * 先将Map集合中的键值对转换为一个List集合；
		 * 这是不加泛型的简写形式：
		 * list list = new ArrayList(map.entrySet());
		 *  为了便于理解，这是分步的写法：  
		 *  先将Map集合用entrySet()转换为一个Collection集合；也就是将键值对两个记录合成为一条记录，jdk是用“=”作为分割符；例：A=100;
		 * 		Collection<Entry<string, Integer>> coll = map.entrySet();
		 *  再将Collection通过ArrayList的构造器为ArrayList的形式；
		 *		list<Entry<string,Integer>> list = new ArrayList<Entry<string, Integer>>(coll);
		 */
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		
		System.out.println("排序前：");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		/*
		 * 这里用Collections类中sort()对Map中的值进行排序；
		 * 但sort()中参数是一个实现了Comparator接口方法的对象；其实就是让你自己定义一种排序规则；
		 * 为了方便，下面我这里使的是匿名内部类；
		 */
		Collections.sort(list, new Comparator<Entry<String,Integer>>() {
			
			@Override
			public int compare(Entry<String,Integer> o1, Entry<String,Integer> o2) {
				return o1.getValue().compareTo(o2.getValue()); //升排序
				//return -(o1.getValue().compareTo(o2.getValue()));  //降排序
			}
			
		});
	
		System.out.println("排序后：");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
}

