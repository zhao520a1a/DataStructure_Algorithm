package algorithm.recursion;

import org.junit.Test;

/**
 * 跳台阶问题：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 分析： 台阶有N级的跳法 = 台阶有N-1的跳法 + 台阶有N-2的跳法
 * 可以推出一个公式：S(N) = S(N-1) + S(N-2)    n>2
 * 初始项：S(1)=1,S(2)=2， 类似斐波那契数列，只是初始项不同；
 *
 * Created by golden on 2017/5/2 0002.
 */
public class JumpFloor {



    /*使用递归；时间：O(2^N) */
    public int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;//与斐*不同点
        }
        return s1(n - 1) + s1(n - 2);
    }

    /*使用循环；时间：O(N)*/
    public int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n; //与斐*不同点
        }
        int res = 2;//与斐*不同点
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /*使用矩阵乘法  时间：O(logN)
    * 分析递归式：S(N) = S(N-1) + S(N-2) 这是一个二阶递推数列一定可以用矩阵乘法表示；状态矩阵为2X2矩阵；
    * 详情看斐波那契参考图，只是将状态矩阵（1，1）变为了（2，1）;
    * 将问题转变成求矩阵n次方的问题；
    * */

    /**
     * 两个矩阵相乘的具体实现
     */
    private int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 求矩阵m的p次方
     */
    public int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];

        //先把res设为单位矩阵，即：矩阵对角线上值为1
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        int[][] tmp = m;
        // 矩阵的p次方,将p用二进制数的形式表示，这样将m的p次方分解成多个呈倍数关系的乘方和的结果；
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {   //把二进制中相应位上是1相乘；
                res = muliMatrix(res, tmp);
            }

            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    public int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;  //与斐*不同点
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return  2 *  res[0][0] + res[1][0];  //与斐*不同点
    }


    @Test
    public void test(){
        System.out.println(s1(20));
        System.out.println(s2(20));
        System.out.println(s3(20));
    }



}
