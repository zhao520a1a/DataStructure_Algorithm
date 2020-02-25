package algorithm.bitOps;

/**
 * 统计整数n二进制表达式中1的个数
 * Created by golden on 2017/5/1 0001.
 */
public class NumberOf1ForInt {

    /**
     * 最简单：将n每次无符号右移1位，检查最右边的bit是否为1
     * 因为int占32个bit,所以最坏时要循环32次；
     */
    public int count1(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    /**
     * 该解法中循环的次数只与n的二进制形式中1的个数有关；
     */
    public int count2(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);  //n&(n-1)的操作实质上是抹掉n二进制中最右边的1；
            res++;
        }
        return res;
    }

    /**
     * 该解法中循环的次数只与n的二进制形式中1的个数有关；
     */
    public int count3(int n) {
        int res = 0;
        while (n != 0) {
            //n&(~n + 1)的操作实质上是得到n中最右边的1；然后通过减法抹掉n中最右边的1；
            n -= n & (~n + 1);
            res++;
        }
        return res;
    }

    /**
     * 一种“超自然”的解法:
     * 将bit分组
     * 它是一个类似于归并的过程，将组与组之间的数量合并成一个大组，进行下一步的归并；
     */
    public int count4(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555); //每2个bit为一组，结果描述了分组后1的数量分布
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);//每4个bit为一组
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);//每8个bit为一组
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);//每16个bit为一组
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);//每32个bit为一组
        return n;
    }


}
