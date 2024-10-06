package impleL;

import adtL.QueueADT;

public class QueueD implements QueueADT {
	private class Node {
		int info; //stored value
		Node next; //next node reference
	}

	private Node first; //first element (oldest)
	private Node last; //last element (latest)
	
	public void initialize(){
		first = null;
		last = null;
	}
	
	public void enqueue(int x){
		Node n = new Node();
		n.info = x;
		n.next = null;
		if (last != null) //not empty queue
			last.next = n;
		last = n;
		if (first == null) //the queue was empty
			first = n;
	}
	
	public void dequeue(){
		first = first.next;
		if (first == null) //the queue was emptied
			last = null;
	}
	
	public boolean isEmpty(){
		return (last == null);
	}
	
	public int peek(){
		return first.info;
	}
}
