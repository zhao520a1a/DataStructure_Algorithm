package algorithm.matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @描述：转圈打印矩阵
 * @思路： 矩阵分圈处理
 * 1. 矩阵对角线顶点：初始化(tR,tC)=(0,0), (dR,dC)=(matrix.length-1,matrix[0].length-1)
 * 2. 打印一圈
 * 3. 将对角线顶点向缩小：tR、tC各加1，dR、dC各减1
 * 4. 重复以上过程，直到上顶点坐标跑到下顶点坐标右方或下方： dR<tR || dC<tC
 * @复杂度：空间复杂度O(1)
 * @链接：https://www.nowcoder.com/practice/8ae3701849ae450dac56ad2b704fa57d?tpId=101&tqId=33217&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
public class SpiralOrderPrint {

    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            pringEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }


    //打印子矩阵一圈
    private static void pringEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) { //子矩阵只有一行时
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) { //子矩阵只有一列时
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {  //一般情况
            int currR = tR;
            int currC = tC;
            while (currC != dC) {
                System.out.print(m[tR][currC] + " ");
                currC++;
            }
            while (currR != dR) {
                System.out.print(m[currR][dC] + " ");
                currR++;
            }
            while (currC != tC) {
                System.out.print(m[dR][currC] + " ");
                currC--;
            }
            while (currR != tR) {
                System.out.print(m[currR][tC] + " ");
                currR--;
            }
        }
    }


}


class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] tmp = bf.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                nums[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        SpiralOrderPrint.spiralOrderPrint(nums);
    }

}