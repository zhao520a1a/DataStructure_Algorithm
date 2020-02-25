package algorithm.sort;

class BubbleSort {
	public static void main(String [] args){
		int[] a = {1,4,6,7,2,9,8};
		
		bubbleSort(a);
		
		for(int i: a){
			System.out.println(i);
		
		}
	
	}
	
	public static int[] bubbleSort(int[] a) {
		
		for(int i=0; i<a.length-1; i++) {
			for(int j=0; j<a.length-1-i; j++) {
				if(a[j] > a[j+1]){
					int t;
					t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
				}
			}
		}
		return a;
	}
	
}