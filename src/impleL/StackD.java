package impleL;

import adtL.StackADT;

public class StackD implements StackADT {
	private class Node {
		int info; //stored value
		Node next; //next node reference
	}
	
	private Node first; //reference to the structure
	
	public void initialize() {
		first = null;
	}
	
	public void push(int x) { //beginning adding
		Node n = new Node();
		n.info = x;
		n.next = first;
		first = n;
	}
	
	public void pop() {
		first = first.next;
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public int peek() {
		return first.info;
	}
}