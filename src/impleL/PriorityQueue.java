package impleL;

import adtL.PriorityQueueADT;

public class PriorityQueue implements PriorityQueueADT {
	private class Elemento {
		int value; //same as int value, priority; 
		int priority;
	}
	
	private Elemento[] elements;
	private int index;
	
	public void initialize() {
		index = 0;
		elements = new Elemento[100];
	}
	
	public void priorityEnqueue(int x, int prio) {
		int j = index;
		//elements are moved right while its priority is greater or equal to prio
		while (j > 0 && elements[j-1].priority >= prio) {
			elements[j] = elements[j-1];
			j--;
		}
		elements[j] = new Elemento();
		elements[j].value = x;
		elements[j].priority = prio;
		index++;
	}
	
	public void dequeue() {
		//elements[index - 1] = null;
		index--;
	}
	
	public int peek() {
		return elements[index-1].value;
	}
	
	public int priority() {
		return elements[index-1].priority;
	}
	
	public boolean isEmpty() {
		return (index == 0);
	}
}
