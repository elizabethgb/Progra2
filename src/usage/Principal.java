package usage;

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
import adtL.StackADT;

public class Principal {

	//EXTERNAL METHODS (stack, queue, priority queue, set, simple dictionary and multiple dictionary)

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
	
	public static boolean isPalindrome(StackADT original) {
		int countElem = 0;
		StackADT aux = new Stack();
		aux.initialize();
		while(!original.isEmpty()) {
			countElem++;
			aux.push(original.peek());
			original.pop();
		}
		
		QueueADT auxQueue = new Queue();
		auxQueue.initialize();
		while(!aux.isEmpty()) {
			int num = aux.peek();
			original.push(num);
			auxQueue.enqueue(num);
			aux.pop();
		}

		int comparisons = countElem/2; //integer division
		boolean resp = true;
		while(comparisons>0 && resp) {
			comparisons--;
			if (original.peek() == auxQueue.peek()) {
				original.pop();
				auxQueue.dequeue();
			} else
				resp = false;
		}

		return resp;
	} //postcondition: original stack is destroyed (if I don't want to, I can make a copy)
	
	public static void showSimpleDic(SimpleDicADT sd) {
		SetADT keys = sd.keys();
		while (!keys.isEmpty()) {
			int key = keys.select();
			int value = sd.get(key);
			System.out.println(key + ": " + value);
			keys.remove(key);
		}
	}

	public static void showSet(SetADT s) {
		while (!s.isEmpty()) {
			int elem = s.select();
			System.out.print(elem + ".");
			s.remove(elem);
		}
	} //postcondition: the set is destroyed (we could've made a copy to avoid that)

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
	
	static SetADT copySet(SetADT s) {
		//move everything to an auxiliary set
		SetADT aux = new Set();
		aux.initialize();;
		while (!s.isEmpty()) {
			int num = s.select();
			aux.add(num);
			s.remove(num);
		}
		//move everything from the auxiliary to the copy and the original at the same time
		SetADT copy = new Set();
		copy.initialize();;
		while (!aux.isEmpty()) {
			int num = aux.select();
			copy.add(num);
			s.add(num); //restore the original
			aux.remove(num);
		}
		return copy;
	}
	
	static QueueADT cartesianP(SetADT s1, SetADT s2) {
		QueueADT respQueue = new Queue();
		respQueue.initialize();
		
		SetADT s1Copy = copySet(s1); //I copy c1 (to not lose it)
		
		while (!s1Copy.isEmpty()) {
			int numS1 = s1Copy.select();
			SetADT s2Copy = copySet(s2); //I copy c2 (to not lose it)
			while(!s2Copy.isEmpty()) {
				int numS2 = s2Copy.select();
				respQueue.enqueue(numS1);
				respQueue.enqueue(numS2);
				s2Copy.remove(numS2);
			}
			s1Copy.remove(numS1);
		}
		
		return respQueue;
	}
	
	//a and b are destroyed
	public static SetADT union(SetADT a, SetADT b) {
		SetADT resp = new Set();
		resp.initialize();;
		while (!a.isEmpty()) {
			int num = a.select();
			resp.add(num);
			a.remove(num);
		}
		while (!b.isEmpty()) {
			int num = b.select();
			resp.add(num);
			b.remove(num);
		}
		return resp;
	}
	
	static SetADT intersection(SetADT s1, SetADT s2) {
		SetADT inter = new Set();
		inter.initialize();;
		SetADT s1Copy = copySet(s1);
		while (!s1Copy.isEmpty()) {
			int elem = s1Copy.select();
			if (s2.contains(elem))
				inter.add(elem);
			s1Copy.remove(elem);
		}
		return inter;
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
	
	//intersection of keys and intersection of values from 2 multiple dictionaries
	static MultipleDicADT intKeysIntValues(MultipleDicADT d1, MultipleDicADT d2) {
		MultipleDicADT resp = new MultipleDic();
		resp.initialize();
		SetADT d1Keys = d1.keys();
		SetADT d2Keys = d2.keys();
		SetADT keysInter = intersection(d1Keys, d2Keys);
		while (!keysInter.isEmpty()) {
			int key = keysInter.select();
			SetADT d1Values = d1.get(key);
			SetADT d2Values = d2.get(key);
			SetADT valuesInter = intersection(d1Values, d2Values);
			while (!valuesInter.isEmpty()) { //if the set of values is empty, it doesn't get in and it doesn't add anything from that key 
				int value = valuesInter.select();
				resp.add(key, value);
				valuesInter.remove(value);
			}
			keysInter.remove(key);
		}
		return resp;
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

		/*QueueADT queue1 = new Queue();
		queue1.initialize();;
		queue1.enqueue(25);
		queue1.enqueue(83);
		queue1.enqueue(0);
		queue1.enqueue(2);
		QueueADT copyOfQueue1 = copyQueue(queue1);
		//we work with the copy (so the original is not lost)*/
		
		/*StackADT stack1 = new Stack();
		stack1.initialize();
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(2);
		stack1.push(1);
		System.out.println(isPalindrome(stack1));*/
		
		/*SetADT s1 = new Set();
		s1.initialize();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		SetADT s2 = new Set();
		s2.initialize();
		s2.add(5);
		s2.add(6);
		
		QueueADT resp1 = cartesianP(s1, s2);
		while (!resp1.isEmpty()) {
			System.out.println(resp1.peek());
			resp1.dequeue();;
		}*/
		
		/*MultipleDicADT d1 = new MultipleDic();
		d1.initialize();
		d1.add(1, -5);
		d1.add(1, 1);
		d1.add(1, 2);
		d1.add(1, 3);
		d1.add(2, 1);
		d1.add(2, 2);
		d1.add(2, 3);
		d1.add(3, 2);
		d1.add(3, 4);
		d1.add(3, 6);
		d1.add(4, 1);
		d1.add(4, 4);
		d1.add(4, 5);
		d1.add(5, 2);
		d1.add(5, 3);
		d1.add(5, 5);
		MultipleDicADT d2 = new MultipleDic();
		d2.initialize();
		d2.add(1, 1);
		d2.add(1, 2);
		d2.add(1, 3);
		d2.add(1, 4);
		d2.add(3, 3);
		d2.add(3, 5);
		d2.add(3, 7);
		d2.add(3, 9);
		d2.add(5, 1);
		d2.add(5, 2);
		d2.add(5, 3);
		d2.add(5, 4);
		d2.add(7, 5);
		d2.add(7, 7);
		d2.add(7, 9);
		d2.add(7, 10);
		MultipleDicADT resp2 = intKeysIntValues(d1, d2);
		SetADT keys = resp2.keys();
		while (!keys.isEmpty()) {
			int key = keys.select();
			SetADT values = resp2.get(key);
			while (!values.isEmpty()) {
				int value = values.select();
				System.out.println(key + " " + value);
				values.remove(value);
			}
			keys.remove(key);
		}*/
	}

}
