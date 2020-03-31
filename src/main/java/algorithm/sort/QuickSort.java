package algorithm.sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = {5, 3, 2, 7, 9};
		int[] b = new int[10];
		quickSort(a, 0, a.length - 1);
		for(int i: a) {
			System.out.print(i + " ");
		}
	}

	/**快速排序方法*/
	/**a：要排序的数组
	 * start:这里选取的是第一个数，这里是索引号，始终拿这个数和其他数比较，
	 * end；最后一个元素，这里是索引号；
	 */
	public static void quickSort(int[] a, int start, int end) {
		//flag表示快排时游标移动的方向；若为false表“左->右”，若为true表“右->左”；
		boolean flag = true;
		int i = start;
		int j = end;

		if(i >= j) { // 判断是否到中间了,这也是递归的结束条件；
			return ;
		}

		while(i != j) {
			//若是前面的数大于后面的数，那么两个数就交换；
			if(a[i] > a[j]) {
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
				flag = !flag;//*****只有经过数的交换后，才能把游标移动的方向改变；
			}
			//根据flag的值决定下标移动，还是上标移动 ，使游标更改走下一个数据；
			if(flag)
				j--;
			else
				i++;
		}
		//上面的循环结束后，i和j都指向同一位置；要进行下一次的排序，就要将i,j分别移向两边；
		i--;
		j++;
		//通过递归实现整体的快速排序；
		quickSort(a, start, i);
		quickSort(a, j, end);
	}
}


