package training;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/13
 */

public class HashCode {

    public static void main(String[] args) {
        Integer var = 1;
        System.out.println(var.hashCode());
        System.out.println(System.identityHashCode(var));  // JVM内部的，无法通过重写hashCode()方法修改；


        HashMap map = new HashMap();
        map.put("name", "golden");
    }


    public class PhoneNumber {
        int numbersOne;
        int numbersTwo;
        int numbersThree;

        public PhoneNumber(int numbersOne, int numbersTwo, int numbersThree) {
            this.numbersOne = numbersOne;
            this.numbersTwo = numbersTwo;
            this.numbersThree = numbersThree;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PhoneNumber)) return false;
            PhoneNumber that = (PhoneNumber) o;
            return Objects.equals(numbersOne, that.numbersOne) &&
                    Objects.equals(numbersTwo, that.numbersTwo) &&
                    Objects.equals(numbersThree, that.numbersThree);
        }
    }


    /**
     * 为什么覆盖 equals() 一定要覆盖 hashCode()
     */
    @Test
    public void testHashCodeAndEquals() {
        Map numberMap = Maps.newHashMap();
        PhoneNumber obj1 = new PhoneNumber(707, 867, 5309);
        numberMap.put(obj1, "Jenny");
        System.out.println(numberMap.get(obj1));
        System.out.println(numberMap.get(new PhoneNumber(707, 867, 5309)));
    }
}
