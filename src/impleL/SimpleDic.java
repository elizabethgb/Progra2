package impleL;

import adtL.SetADT;
import adtL.SimpleDicADT;

public class SimpleDic implements SimpleDicADT {
	private class Element {
		int key;
		int value;
	}
	private Element[] elements;
	private int count;
	
	public void initialize() {
		count = 0;
		elements = new Element[100];
	}
	
	public void add(int key, int value) {
		int pos = this.key2Index(key);
		if (pos == -1) { //key doesn't exist -> new entry
			elements[count] = new Element();
			elements[count].key = key;
			elements[count].value = value;
			count++;
		} else {
			elements[pos].value = value;
		}
	}
	
	private int key2Index(int key) { //if key isn't found, it returns -1
		int i = count - 1;
		while (i >= 0 && elements[i].key != key)
			i--;
		return i;
	}
	
	public void remove(int key) {
		int pos = key2Index(key);
		if (pos != -1) { //key exists
			elements[pos] = elements[count-1];
			count--;
		}
	}
	
	public int get(int key) { //remember: key must exist (precondition)
		int pos = key2Index(key);
		return elements[pos].value;
	}
	
	public SetADT keys() {
		SetADT s = new Set();
		s.initialize();
		for (int i=0; i < count; i++) {
			s.add(elements[i].key);
		}
		return s;
	}
}
