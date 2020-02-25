package algorithm.recursion;

import org.junit.Test;

/**
 * 牛生小牛问题： 类似解决斐波那契数列的方法
 * 农场中有1头成熟母牛，从第二年开始就可以生1头小牛，而小牛3年成熟之后，又可以生小母牛；
 * 假定所生的的均为母牛且永远不会死，按照以上牛生牛原则，请问n年后农场有多少牛？
 * 可以推出一个公式：C(n) = C(n-1)+C(n-3)    n>3
 * 注：注释掉的代码是解决斐波那契方法时代码，为了进行直观的比较；
 * Created by golden on 2017/5/2 0002.
 */
public class BronCows {


    /*使用递归；时间：O(2^N) */
    public int c1(int n) {
        if (n < 1) {
            return 0;
        }
//        if (n == 1 || n == 2) {
//            return 1;
//        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    /*使用循环；时间：O(N)*/
    public int c2(int n) {
        if (n < 1) {
            return 0;
        }
//        if (n == 1 || n == 2) {
//            return 1;
//        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
//        number res = 1;
//        number pre = 1;
//        number tmp = 0;
//        for (number i = 3; i <= n; i++) {
//            tmp = res;
//            res = res + pre;
//            pre = tmp;
//        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    /*使用矩阵乘法  时间：O(logN)
    * 分析递归式：C(n) = C(n-1)+C(n-3) 这是一个三阶递推数列一定可以用矩阵乘法表示；
    * 详情看《程序员代码面试指南》P186页例题
    * 状态矩阵为3X3矩阵；
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

    public int c3(int n) {
        if (n < 1) {
            return 0;
        }
//        if (n == 1 || n == 2) {
//            return 1;
//        }
//        number[][] base = {{1, 1}, {1, 0}};
//        number[][] res = matrixPower(base, n - 2);
//        return res[0][0] + res[1][0];
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    @Test
    public void test() {
        System.out.println(c1(20));
        System.out.println(c2(20));
        System.out.println(c3(20));
    }

}
