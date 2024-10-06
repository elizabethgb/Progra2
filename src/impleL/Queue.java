package impleL;

import adtL.QueueADT;

public class Queue implements QueueADT {

	private int[] arr;
	private int index;

	@Override
	public void initialize() {
		arr = new int[100];
		index = 0;
	}

	@Override
	public void enqueue(int x) {
		for (int i=index-1; i>=0; i--) {
			arr[i+1] = arr[i];
		}
		arr[0] = x;
		index++;
	}

	@Override
	public void dequeue() {
		index--; //index = index-1 //index -= 1
	}

	@Override
	public int peek() {
		return arr[index-1];
	}

	@Override
	public boolean isEmpty() {
		return (index == 0);
		/*if (index == 0)
			return true;
		else
			return false;*/
	}

}
