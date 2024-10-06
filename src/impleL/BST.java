package impleL;

import adtL.BSTADT;

public class BST implements BSTADT {
	private class BSTNode {
		int info;
		BSTADT lC;
		BSTADT rC;
	} 
	private BSTNode t;

	public void initialize(){
		t = null;
	}

	public int root(){
		return t.info;
	}

	public boolean isEmpty(){
		return (t == null);
	}

	public BSTADT rightChild(){
		return t.rC;
	}

	public BSTADT leftChild(){
		return t.lC;
	}

	private int maximum(BSTADT t){
		if (t.rightChild().isEmpty())
			return t.root(); //we arrived
		else
			return maximum(t.rightChild()); //we haven't arrive yet
	}

	private int minimum(BSTADT t){
		if (t.leftChild().isEmpty())
			return t.root(); //we arrived
		else
			return minimum(t.leftChild()); //we haven't arrive yet
	}

	 
	public void addElem(int x){
		if (t == null) { //empty tree
			t = new BSTNode();
			t.info = x;
			t.lC = new BST();
			t.lC.initialize();
			t.rC = new BST();
			t.rC.initialize();
		} else if (t.info > x) //left tree case
			t.lC.addElem(x);
		else if (t.info < x) //right tree case
			t.rC.addElem(x);
		else //it was found
			; //nothing to do
	}

	public void removeElem(int x){
		if (t != null) { //not empty tree check
			if (t.info == x && t.lC.isEmpty() && t.rC.isEmpty()) { //it's a leaf
				t = null;
			} else if (t.info == x && !t.lC.isEmpty()) {
				int mayor = maximum(t.lC);
				t.info = mayor; //it's replaced with the greatest of the least
				t.lC.removeElem(mayor);
			} else if (t.info == x && t.lC.isEmpty()) { //!a.rC.isEmpty()
				int menor = minimum(t.rC);
				t.info = menor; //it's replaced with the least of the greatest
				t.rC.removeElem(menor);
			} else if (t.info < x) { //we keep looking for the greatest (right)
				t.rC.removeElem(x);
			} else { //a.info > x
				t.lC.removeElem(x); //we keep looking for the least (left)
			}
		} else //it wasn't found
			; //nothing to do
	}
} 
