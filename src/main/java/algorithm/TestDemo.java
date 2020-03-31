package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/24
 */

public class TestDemo {

    public static void main(String[] args) throws IOException {


        int[] arr = {1, 2, 3};
        int[] arr1 = new int[10];

        int[][] arr2 = {{1, 2}, {3, 4},{5,6}};
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 2; i1++) {
                System.out.println(arr2[i][i1]);
            }
        }


        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(sc.readLine());
        for (int i = 0; i < row; i++) {
            String[] str = sc.readLine().split(" ");
            if ("push".equals(str[0])) {
                continue;
            }
            if ("pop".equals(str[0])) {
                continue;
            }
        }



    }

}
