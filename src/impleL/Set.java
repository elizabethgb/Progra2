package impleL;

import adtL.SetADT;

public class Set implements SetADT {
	private int[] a;
	private int count;
	
	public void initialize() {
		a = new int[100];
		count = 0;
	}
	
	public void add(int x) {
		if (!this.contains(x)){
			a[count] = x;
			count++;
		}
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}
	
	public int select() { //any
		/*int max = count-1;
		int min = 0;
		int pos = (int)(Math.random() * (max-min+1) + min);
		return a[pos];*/
		return a[0]; //a[index-1];
	}
	
	public boolean contains(int x) {
		int i = 0;
		while (i < count && a[i] != x)
			i++;
		return (i < count);
		/*boolean resp;
		if (i<count)
			resp = true;
		else
			resp = false;	
		return resp;*/
	}
	
	public void remove(int x) {
		int i = 0;
		while (i < count && a[i] != x)
			i++;
		if (i < count){ //it was found
			a[i] = a[count-1];
			count--;
		}
	}
}