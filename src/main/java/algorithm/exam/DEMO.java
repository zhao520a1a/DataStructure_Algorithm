package algorithm.exam;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * 贪心算法
 *
 * @author Golden
 */
public class DEMO {
    public int num;
    public int[] arr1;
    public int[] arr2;
    public int sum;
    public List list = new ArrayList();

    public static double startTime;
    public static double endTime;


    public static void main(String[] args) {
        startTime = System.currentTimeMillis();

        DEMO main = new DEMO();
        main.input();
        main.calculateValue();

        getRuntime();
    }

    public static void getRuntime() {
        endTime = System.currentTimeMillis();
        System.out.println("Basic Statements take(基本语句用时)"
                + (endTime - startTime) + " milliseconds!");
    }

    public void calculateValue() {

    }

    public void input() {
        Scanner in = new Scanner(System.in);
        num = in.nextInt();
        arr1 = new int[num];
        arr2 = new int[num];

        for (int i = 0; i < num; i++) {
            arr2[i] = in.nextInt();
        }
        for (int i = 0; i < num; i++) {
            arr1[i] = in.nextInt();
        }
        sum = in.nextInt();
    }


    void myPrintList() {
        for (int i = 0; i < list.size(); i++)
            System.out.print("  " + list.get(i));
        System.out.println();
    }
}