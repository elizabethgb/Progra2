package usage;

import adtL.BSTADT;
import adtL.SetADT;
import impleL.BST;
import impleL.Set;

public class BSTmethods {
	
	//BST external methods
	
	public void preOrder(BSTADT t) {
		if (!t.isEmpty()) {
			System.out.println(t.root());
			preOrder(t.leftChild());
			preOrder(t.rightChild());
		}
	}
	
	public void inOrder(BSTADT t) {
		if (!t.isEmpty()) {
			inOrder(t.leftChild());
			System.out.println(t.root());
			inOrder(t.rightChild());
		}
	}
	
	public void postOrder(BSTADT t) {
		if (!t.isEmpty()) {
			postOrder(t.leftChild());
			postOrder(t.rightChild());
			System.out.println(t.root());
		}
	}
	
	public static boolean isItIn(int elem, BSTADT t) {
		if (t.isEmpty())
			return false;
		else if (t.root() == elem)
			return true;
		else if (t.root() > elem)
			return isItIn(elem, t.leftChild());
		else //a.root() < elem
			return isItIn(elem, t.rightChild());
	}
	
	public static boolean isLeaf(int elem, BSTADT t) {
		if (t.isEmpty())
			return false;
		else if (t.root() == elem && t.leftChild().isEmpty() && t.rightChild().isEmpty())
			return true;
		else if (t.root() == elem && (!t.leftChild().isEmpty() || !t.rightChild().isEmpty()))
			return false;
		else if (t.root() > elem) //a.root() != elem
			return isLeaf(elem, t.leftChild());
		else // a.root() < elem //a.root() != elem
			return isLeaf(elem, t.rightChild());
	}
	
	//precondition: the element is in the tree
	public static int depth(int elem, BSTADT t) {
		if (t.root() == elem)
			return 0;
		else if (t.root() > elem)
			return 1 + depth(elem, t.leftChild());
		else //a.root() < elem
			return 1 + depth(elem, t.rightChild());
	}
	
	public static int depth2(int elem, BSTADT t) { //if the element is not in the tree, it returns -1
		if (t.isEmpty())
			return -1;
		else if (t.root() == elem)
			return 0;
		else if (t.root() > elem) {
			if (isItIn(elem, t.leftChild()))
				return 1 + depth2(elem, t.leftChild());
			else
				return -1;
		}
		else { //a.root() < elem
			if (isItIn(elem, t.rightChild()))
				return 1 + depth2(elem, t.rightChild());
			else
				return -1;
		}
	}
	
	//precondition: the tree is not empty
	public static int minorElem(BSTADT t) {
		if (t.leftChild().isEmpty())
			return t.root(); //we arrived
		else 
			return minorElem(t.leftChild()); //we haven't arrive yet
	}
	
	//precondition: the tree is not empty
	public static int greatestElem(BSTADT t) {
		if (t.rightChild().isEmpty())
			return t.root();
		else
			return greatestElem(t.rightChild());
	}
	
	public static int height(BSTADT t) {
		if(!t.isEmpty())
			return Math.max(height(t.leftChild()), height(t.rightChild())) + 1;
		else //I fell from the tree
			return -1;
	}
	
	//precondition: t initialized and not empty
	public static int height2(BSTADT t) {
		//1st step (general rule): check if it's empty -> not qualified
		//2nd step: declaration or the response
		int resp;
		//3rd step: possibilities, cases (base cases/recurssives passes)
		if(t.leftChild().isEmpty() && t.rightChild().isEmpty()) { //it's a leaf
			resp = 0;
		} else {
			if (t.leftChild().isEmpty()) //RC is not empty
				resp = height2(t.rightChild()) + 1;
			else if (t.rightChild().isEmpty()) //LC is not empty
				resp = height2(t.leftChild()) + 1;
			else { //RC and LC are not empty
				int leftH = height2(t.leftChild());
				int rightH = height2(t.rightChild());
				resp = leftH > rightH ? leftH + 1 : rightH + 1;
				/*if(leftH>rightH)
						resp=leftH+1;
					else
						resp=rightH+1;*/
			}
		}
		return resp;
	}

	public static int countNodes(BSTADT tree) {
		if (tree.isEmpty())
			return 0; //base case
		else
			return 1 + countNodes(tree.leftChild()) + countNodes(tree.rightChild()); //recursive pass
	}
	
	public static int countNodes2(BSTADT tree) {
		int resp = 0;
		if (!tree.isEmpty()) {
			int leftCount = countNodes2(tree.leftChild());
			int rightCount = countNodes2(tree.rightChild());
			resp = 1 + leftCount + rightCount;
		}
		return resp;
	}
	
	public static int countNodes3(BSTADT t) {
		int resp = 0;
		if (!t.isEmpty()) {
			resp += 1;
			if (!t.leftChild().isEmpty())
				resp += countNodes3(t.leftChild());
			if (!t.rightChild().isEmpty())
				resp += countNodes3(t.rightChild());
		} 
		return resp;
	}
	
	public static int aditionElem(BSTADT t) {
		if (t.isEmpty())
			return 0;
		else 
			return t.root() + aditionElem(t.leftChild()) + aditionElem(t.rightChild());
	}
	
	public static int countLeaves(BSTADT t) {
		if (t.isEmpty())
			return 0;
		else if (t.leftChild().isEmpty() && t.rightChild().isEmpty()) //it's a leaf
			return 1;
		else //it's not empty and it's not a leaf, it's a common node
			return countLeaves(t.leftChild()) + countLeaves(t.rightChild());
	}
	
	public static boolean sameShape(BSTADT a, BSTADT b) {
		if (a.isEmpty())
			/*if (b.isEmpty())
				return true;
			else
				return false;*/
			return b.isEmpty();
		else if (b.isEmpty()) //!a.isEmpty()
			return false;
		else //!a.isEmpty() && !b.isEmpty()
			return sameShape(a.leftChild(), b.leftChild()) && sameShape(a.rightChild(), b.rightChild());
	}
			
	public static boolean sameTrees(BSTADT a, BSTADT b) {
		if (a.isEmpty())
			return b.isEmpty();
		else if (b.isEmpty()) //!a.isEmpty
			return false;
			/*if (a.root() == b.root())
				return sameTrees(a.leftChild(), b.leftChild()) && sameTrees(a.rightChild(), b.rightChild());
			else
				return false;*/
		else //!a.isEmpty && !b.isEmpty()
			return (a.root() == b.root()) && sameTrees(a.leftChild(), b.leftChild()) && sameTrees(a.rightChild(), b.rightChild());	
	}
	
	public static int countElemLevelK(BSTADT t, int k) {
		if (t.isEmpty())
			return 0;
		else if (k==0) //I arrived to the level I should count
			return 1;
		else 
			return countElemLevelK(t.leftChild(), k-1) + countElemLevelK(t.rightChild(), k-1);
	}
	
	//greater elements than k
	public static SetADT greater(BSTADT t, int k) {
		if (t.isEmpty()) {
			SetADT resp = new Set();
			resp.initialize();
			return resp;
		} else if (k > t.root() || k == t.root())
			return greater(t.rightChild(), k);
		else { //k < a.root()
			SetADT resp = Principal.union(greater(t.leftChild(), k), greater(t.rightChild(), k));
			resp.add(t.root());
			return resp;
		}
	}
	
	//v exists in the tree
	public static int previous(BSTADT t, int v) {
		if (v < t.root())
			return previous(t.leftChild(), v);
		else if (v > t.root()) {
			int num = previous(t.rightChild(), v);
			if (num != v)
				return num;
			else //it was found in a branch which hasn't got LC and it comes coming up
				return t.root();
		} else { //v == a.root()
			if (!t.leftChild().isEmpty())
				return greatestElem(t.leftChild());
			else //t.leftChild().isEmpty()
				return v; //flag
		}
	}

	//set of leaves
	public static SetADT leaves(BSTADT t) {
		SetADT resp = new Set();
		resp.initialize();
		if (t.isEmpty())
			return resp;
		else if (t.leftChild().isEmpty() && t.rightChild().isEmpty()) {
			resp.add(t.root());
			return resp;
		} else
			return Principal.union(leaves(t.leftChild()), leaves(t.rightChild()));
	}

	//precondition: num is in the tree
	public static SetADT descendants(BSTADT t, int num) {
		SetADT resp = new Set();
		resp.initialize();
		if (t.isEmpty()) //it doesn't get in here ever
			return resp;
		else if (num < t.root())
			return descendants(t.leftChild(), num);
		else if (num > t.root())
			return descendants(t.rightChild(), num);
		else //num == a.root()
			return Principal.union(loadTreeToSet(t.leftChild()), loadTreeToSet(t.rightChild()));
	}

	public static SetADT loadTreeToSet(BSTADT t) {
		SetADT resp;
		if (t.isEmpty()) {
			resp = new Set();
			resp.initialize();
		} else {
			resp = Principal.union(loadTreeToSet(t.leftChild()), loadTreeToSet(t.rightChild()));
			resp.add(t.root());
		}
		return resp;
	}
	
	//set of elements at odd levels
	public static SetADT elemOddLevels(BSTADT t) {
		return elemOddLevels(t, 0);
	}
	
	public static SetADT elemOddLevels(BSTADT t, int l) {
		SetADT odds;
		if (t.isEmpty()) {
			odds = new Set();
			odds.initialize();
		} else {
			odds = Principal.union(elemOddLevels(t.leftChild(), l+1), elemOddLevels(t.rightChild(), l+1));
			if (l%2!=0)
				odds.add(t.root());
		}
		return odds;
	}
	
	public static SetADT elemOddLevels2(BSTADT t) {
		SetADT odds = new Set();
		odds.initialize();
		elemOddLevels2(t, false, odds);
		return odds;
	}
	
	public static void elemOddLevels2(BSTADT t, boolean isOdd, SetADT odds) {
		if (!t.isEmpty()) {
			elemOddLevels2(t.leftChild(), !isOdd, odds);
			elemOddLevels2(t.rightChild(), !isOdd, odds);
			if (isOdd)
				odds.add(t.root());
		}
	}

	public static void main(String[] args) {
		//test place
		//ADTs are declared, they are loaded with data, external methods are called
		
		/*BSTADT t = new BST();
		t.initialize();
		t.addElem(10);
		t.addElem(15);
		t.addElem(8);
		t.addElem(9);
		t.addElem(12);*/
	}

}
