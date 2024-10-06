package impleL;

import adtL.StackADT;

public class Stack implements StackADT {
	
	private int[] arr;
	private int index;

	@Override
	public void initialize() {
		arr = new int[100];
		index = 0;
	}

	@Override
	public void push(int x) {
		arr[index] = x;
		index++; //index+=1 //index = index+1
	}

	@Override
	public void pop() {
		index--;
	}

	@Override
	public int peek() {
		return arr[index-1];
	}

	@Override
	public boolean isEmpty() {
		return index==0;
		/*
		boolean resp;
		if (index==0)
			resp = true;
		else
			resp = false;
		return resp;
		 */
	}

}
