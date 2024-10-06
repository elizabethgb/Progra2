package usage;

import adtL.BSTADT;
import impleL.Queue;
import impleL.PriorityQueue;
import impleL.Set;
import impleL.MultipleDic;
import impleL.Stack;
import adtL.PriorityQueueADT;
import adtL.QueueADT;
import adtL.SetADT;
import adtL.MultipleDicADT;
import adtL.SimpleDicADT;
import adtL.GraphADT;
import adtL.StackADT;

public class Principal {

	//EXTERNAL METHODS

	//precondition: origin initialized
	public static StackADT copyStack(StackADT origin) {
		StackADT copy = new Stack();
		copy.initialize();
		StackADT aux = new Stack();
		aux.initialize();
		while(!origin.isEmpty()) {
			aux.push(origin.peek());
			origin.pop();
		}
		while(!aux.isEmpty()) {
			int elem = aux.peek();
			copy.push(elem);
			origin.push(elem);
			aux.pop();
		}
		return copy;
	} //postcondition: origin is not destroyed

	//precondition: origin and destiny initialized
	public static void passQueue1(QueueADT origin, QueueADT destiny) {
		while (!origin.isEmpty()) {
			destiny.enqueue(origin.peek());
			origin.dequeue();
		}
	} //postcondition: origin is destroyed

	//precondition: origin initialized
	public static QueueADT passQueue2(QueueADT origin) {
		QueueADT resp = new Queue();
		resp.initialize();
		while(!origin.isEmpty()) {
			int elem = origin.peek();
			resp.enqueue(elem);
			//resp.enqueue(origin.peek()); //same
			origin.dequeue();
		}
		return resp;
	} //postcondition: origin is destroyed

	//precondition: origin initialized
	public static QueueADT copyQueue(QueueADT origin) { //polynomial cost
		QueueADT copy = new Queue(); //C
		copy.initialize(); //C
		QueueADT aux = new Queue(); //C
		aux.initialize(); //C
		while(!origin.isEmpty()) {
			//C
			int elem = origin.peek(); //C
			aux.enqueue(elem); //L
			origin.dequeue(); //C
		} //-> n*(C+C+L+C) -> n*(L) -> P
		while(!aux.isEmpty()) {
			//C
			int elem = aux.peek(); //C
			origin.enqueue(elem); //L
			copy.enqueue(elem); //L
			aux.dequeue(); //C
			//-> C + C + L + L + C -> L
		} //-> P
		return copy;
		//-> C + C + C + C + P + P -> P
	} //postcondition: origin is not destroyed

	//precondition: origin initialized
	public static void passPQto2Queues(PriorityQueueADT origin) {
		QueueADT values = new Queue();
		QueueADT priorities = new Queue();
		values.initialize();
		priorities.initialize();
		while (!origin.isEmpty()) {
			int value = origin.peek();
			int priority = origin.priority();
			values.enqueue(value);
			priorities.enqueue(priority);
			/*same as:
			values.enqueue(origin.peek());
			priorities.enqueue(origin.priority());*/
			origin.dequeue();
		}
		//TODO usage of values and priorities
	} //postcondition: origin is destroyed

	public static boolean includes(SetADT s1, SetADT s2) {
		boolean resp = true;
		while(!s2.isEmpty() && resp) {
			int value = s2.select();
			if(!s1.contains(value))
				resp = false;
			s2.remove(value);
			/* not the same as (because it can result in another element):
			if(!s1.contains(s2.select()))
				resp = false;
			c2.remove(c2.select());*/
		}
		return resp;
	}

	public static StackADT passDicToValuesStack(SimpleDicADT dic) {
		StackADT respStack = new Stack();
		respStack.initialize();
		/*SetADT keys = new Set();
		keys.initialize();
		keys = dic.keys();*/
		SetADT keys = dic.keys();
		while(!keys.isEmpty()) {
			int key = keys.select();
			/*int value = dic.get(key);
			respStack.push(value);*/
			respStack.push(dic.get(key));
			keys.remove(key);
		}
		return respStack;
	}

	public static MultipleDicADT passFromSimpleDicToMult(SimpleDicADT simDic) {
		MultipleDicADT mulDic = new MultipleDic();
		mulDic.initialize();
		SetADT keysSet = simDic.keys();
		while(!keysSet.isEmpty()) {
			int key = keysSet.select();
			int value = simDic.get(key);
			mulDic.add(key, value);
			keysSet.remove(key);
		}
		return mulDic;
	}

	public static boolean areEqualDictionaries(SimpleDicADT d1, SimpleDicADT d2) {
		boolean resp = true;
		SetADT d1Keys = d1.keys();
		SetADT d2Keys = d2.keys();
		while(!d1Keys.isEmpty() && resp) {
			int key = d1Keys.select();
			d1Keys.remove(key);
			if (!d2Keys.contains(key))
				resp = false; //a key from d1 is not in d2
			else {
				d2Keys.remove(key);
				if (d1.get(key) != d2.get(key))
					resp = false; //the values from d1 and d2 are different
			}
		}
		if (!d2Keys.isEmpty())
			resp = false; //there were more keys in d2 than in d1
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
	
	//preconditions: g initialized, destiny exists in the graph g
	public static SetADT predecesores(GraphADT g, int destiny) {
		SetADT resp = new Set();
		resp.initialize();
		SetADT vertices = g.vertices();
		while(!vertices.isEmpty()) {
			int origin = vertices.select();
			if(g.existsEdge(origin, destiny))
				resp.add(origin);
			vertices.remove(origin);
		}
		return resp;
	}
	//postconditions: the method returns the predecessors set
	
	//precondition: t initialized and not empty
	public static int height(BSTADT t) {
		//1st step (general rule): check if it's empty -> not qualified
		//2nd step: declaration or the response
		int resp;
		//3rd step: possibilities, cases (base cases/recurssives passes)
		if(t.leftChild().isEmpty() && t.rightChild().isEmpty()) { //it's a leaf
			resp = 0;
		} else {
			if (t.leftChild().isEmpty()) //RC is not empty
				resp = height(t.rightChild()) + 1;
			else if (t.rightChild().isEmpty()) //LC is not empty
				resp = height(t.leftChild()) + 1;
			else { //RC and LC are not empty
				int leftH = height(t.leftChild());
				int rightH = height(t.rightChild());
				resp = leftH > rightH ? leftH + 1 : rightH + 1;
				/*if(leftH>rightH)
					resp=leftH+1;
				else
					resp=rightH+1;*/
			}
		}
		return resp;
	}
	
	public static int height2(BSTADT t) {
		if(!t.isEmpty())
			return Math.max(height2(t.leftChild()), height2(t.rightChild())) + 1;
		else
			return -1;
	}
	
	public static void orderedKeys(MultipleDicADT dic) { //Polynomial cost
		PriorityQueueADT auxPQ = new PriorityQueue(); //C
		auxPQ.initialize(); //C
		/*SetADT keys = new Set();
		keys.initialize();*/
		SetADT keys = dic.keys(); //C+C+P -> P
		//int key;
		while(!keys.isEmpty()) {
			//C
			int key = keys.select(); //C
			keys.remove(key); //L
			auxPQ.priorityEnqueue(1, key); //the value is not important (the priority is) //L
		} //-> P
		//keys already ordered in the priorities of the PQ
		while(!auxPQ.isEmpty()) {
			//C
			int key = auxPQ.priority(); //C
			System.out.println(key); //printing the keys in order //C
			auxPQ.dequeue(); //C
		} //-> L
	} //-> C+C+P+P+L -> P

	public static void main(String[] args) {
		//test place
		//ADTs are declared, they are loaded with data, external methods are called

		QueueADT queue1 = new Queue();
		queue1.initialize();;
		queue1.enqueue(25);
		queue1.enqueue(83);
		queue1.enqueue(0);
		queue1.enqueue(2);
		QueueADT copyOfQueue1 = copyQueue(queue1);
		//we work with the copy (so the original is not lost)
	}

}
