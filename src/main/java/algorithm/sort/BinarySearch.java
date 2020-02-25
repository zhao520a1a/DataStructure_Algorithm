package algorithm.sort;

import java.util.*;

class BinarySearch {
	public static void main(String[] args){
		int[] a  = {4,3,5,6,8,2,9,1};
		Arrays.sort(a);
		System.out.println(binarySearch(a, 4));
	
	}
	
	public static int binarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
   
		while(low <= high) {
			int mid = (low+high) / 2;
			if(key > a[mid]) {
				low = mid + 1;
			} else if(key < a[mid]) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

}