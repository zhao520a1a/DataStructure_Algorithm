package algorithm.string;

import org.junit.Test;

/**
 * 自定义方法将字符串转为整数；
 * 输入：输入一个字符串,包括数字字母符号,可以为空；（正整数正确的输入形式：“123”或“+123”   错误形式：“012”或“+012” ）
 * 输入：如果是合法的数值表达则返回该数字，否则返回0
 * Created by golden on 2017/4/28 0028.
 */
public class StrToInt {


    /*判断字符串是否符合转换的要求;合法返回true，非法返回false;
    * 不符合的条件：满足任意一条，说明该字符串非法，返回false;
    *    条件1：没有以“-”、“+”或数字开头，
    *    条件2：“-”和“-0XX”
    *    条件3：“+”和“+0XX”
    *    条件4：“0XX”
    *    条件5：其中存在非数字字符的字符
    * */
    public boolean isValid(char[] chars) {
        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) { //条件1
            return false;
        }
        if (chars[0] == '-' && (chars.length == 1 || chars[1] == '0')) {   //条件2
            return false;
        }
        if (chars[0] == '+' && (chars.length == 1 || chars[1] == '0')) {   //条件3
            return false;
        }
        if (chars[0] == '0' && chars.length > 1) {   //条件4
            return false;
        }
        for (int i = 1; i < chars.length; i++) {   //条件5
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }


    /**
     * 因负数比正数拥有更大的绝对值范围（绝对值上：整数最小值比最大值大1），因此在转换过程中一律以负数形式记录绝对值；最终再按符号决定结果到底是什么；
     * 注意：因为以负数记录绝对值，要学会转变思维；
     * 从左往右遍历： res = res * 10 + cur;  （从高位上的数开始）
     * <p>
     * 判断结果是否已经溢出：
     * Integer.MIN_VALUE / 10（最小值除10的商）    Integer.MIN_VALUE % 10（最小值除10的余数）
     * 溢出条件：
     * 条件1：   当前结果 < 【最小值除10的商】 (即：绝对值上大于最小值除10的商), 在加就会溢出；
     * 条件2：   当前结果 == 【最小值除10的商】,  将要加的数小于【最小值除10的余数】(即：绝对值上大于最小值除10的余数), 加上就会溢出；
     * 条件3:    符号为正数    而   saZQ绝对值为【整数最小值】
     **/
    public int strToInt(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }

        int mark = chars[0] == '-' ? -1 : chars[0] == '+' ? 1 : 0;  //代表整数的正负; -1为负，0为不带‘+’的正，1为带‘+’的正；
        int res = 0;   //代表转换的结果
        /*变量minq和minr用于在遍历过程判断res是否已经溢出*/
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        int cur = 0;
        for (int i = mark == 0 ? 0 : 1; i < chars.length; i++) {  //mark等于0，从chars[0]开始，否则从chars[1]开始；
            cur = '0' - chars[i];  //采用负数形式记录绝对值
            if ((res < minq) || (res == minq && cur < minr)) {  //条件1 || 条件2
                return 0;
            }
            res = res * 10 + cur;
        }
        if (mark > -1 && res == Integer.MIN_VALUE) {  //条件3
            return 0;
        }
        return mark > -1 ? -res : res;  //注意：这里res为负数形式
    }


    @Test
    public void test() {
        System.out.println(this.strToInt("+123"));
    }

}
