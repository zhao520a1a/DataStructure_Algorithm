package algorithm.sort;

/**
 * Created by golden on 2017/4/19 0019.
 */
public class Practice {

    public static void main(String[] args) {
        int[] arr = {2, 6, 7, 3, 88, 64, 23, 75, 347};

        quicksort(arr, 0, arr.length-1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quicksort(int[] arr, int start, int end) {
        int i = start, j = end;
        boolean flag = false;

        if (i > j) {
            return;
        }
        while (i != j) {
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                flag = !flag;
            }
            if (flag) {
                i++;
            } else {
                j--;
            }
        }

        i++;
        j--;

        quicksort(arr, start, j);
        quicksort(arr, i, end);
    }


}
