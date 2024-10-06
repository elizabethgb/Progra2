package impleL;

import adtL.SetADT;

public class SetD implements SetADT {
	private class Node {
		int info; //stored value
		Node next; //next node reference
	}
	
	private Node s; //reference to the structure
	
	public void initialize () {
		s = null;
	}
	
	public boolean isEmpty() {
		return (s == null);
	}
	
	public void add(int x) {
		if (!this.contains(x)) {
			Node n = new Node();
			n.info = x;
			n.next = s;
			s = n;
		}
	}
	
	public int select() { //any
		return s.info;
	}
	
	public void remove(int x) { 
		if (s != null) {
			if (s.info == x) { //it's the first
				s = s.next;
			} else { //it's any other, it's looked for)
				Node aux = s;
				while (aux.next != null && aux.next.info != x)
					aux = aux.next;
				if (aux.next != null) //found
					aux.next = aux.next.next;
			}
		}
	}
	
	public boolean contains(int x) {
		Node aux = s; 
		while (aux != null && aux.info != x) 
			aux = aux.next;
		return (aux != null);
	}
}