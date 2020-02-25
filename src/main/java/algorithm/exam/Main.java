package algorithm.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static List<Integer> list;


    public static void main(String[] args) {
        input();
    }


    public static void input() {

        Scanner in = new Scanner(System.in);


        int num = in.nextInt();
        //while (!  (line = in.nextLine()).trim().equals("")) {
        //    //System.out.println(line);
        //    arr2.add( FromNumberSystem26(line.toCharArray()));
        //}
        //
        list = new LinkedList();
        for (int i = 0; i < num; i++) {
            list.add(in.nextInt());
        }

        System.out.println(list.size());


    }
}