package impleL;

import adtL.PriorityQueueADT;

public class PriorityQueueD implements PriorityQueueADT {
	private class PriorityNode {
		int info; //stored value
		int priority; //stored priority
		PriorityNode next; //next node reference
	}
	
	private PriorityNode first; //first element (with highest priority)
	
	public void initialize() {
		first = null;
	}
	
	public void priorityEnqueue(int x, int priority) {
		PriorityNode n = new PriorityNode(); //el nuevo nodo que se acolará
		n.info = x;
		n.priority = priority;
		n.next = null;
		if (first == null || priority > first.priority) {
			n.next = first; //the node must be at the beginning
			first = n;
		} else {
			PriorityNode aux = first;
			while(aux.next != null && aux.next.priority >= priority){
				aux = aux.next;
			}
			n.next = aux.next;
			aux.next = n;
		}
	}
	
	public void dequeue() {
		first = first.next;
	}
	
	public int peek() {
		return first.info ;
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public int priority() {
		return first.priority;
	}
}