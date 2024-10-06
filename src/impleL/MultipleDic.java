package impleL;

import adtL.SetADT;
import adtL.MultipleDicADT;

public class MultipleDic implements MultipleDicADT {
	class Element {
		int key;
		int[] values;
		int countValues;
	}
	
	/*class Element2 {
		int key;
		SetADT values;
	}*/
	
	private Element[] elements;
	private int countKeys;
	
	public void initialize() {
		elements = new Element[100];
		countKeys = 0;
	}
	
	public void add(int key, int value) {
		int posK = key2Index(key);
		if (posK == -1) { //key doesn't exist
			elements[countKeys] = new Element();
			elements[countKeys].key = key;
			elements[countKeys].values = new int[100];
			elements[countKeys].values[0] = value;
			elements[countKeys].countValues = 1;
			countKeys++;
		} else {
			Element e = elements[posK];
			int posV = value2Index(e, value);
			if (posV == -1) { //value doesn't exist
				e.values[e.countValues] = value;
				e.countValues++;
			}
		}
	}
	
	private int key2Index(int key) {
		int i = countKeys - 1;
		while (i >= 0 && elements[i].key != key)
			i--;
		return i;
	}
	
	private int value2Index(Element e, int value) {
		int i = e.countValues-1;
		while (i >= 0 && e.values[i] != value)
			i--;
		return i;
	}
	
	public void remove(int key) {
		int pos = key2Index(key);
		if (pos != -1) { //key exists
			elements[pos] = elements[countKeys-1];
			countKeys--;
		}
	}
	
	public void removeValue(int key, int value) {
		int posK = key2Index(key);
		if (posK != -1) { //key exists
			Element e = elements[posK];
			int posV = value2Index(e, value);
			if (posV != -1) { //value exists
				e.values[posV] = e.values[e.countValues-1];
				e.countValues--;
				if (e.countValues == 0) { //last check
					remove(key);
				}
			}
		}
	}
	
	public SetADT get(int clave) {
		SetADT s = new Set();
		s.initialize();
		int pos = key2Index(clave);
		if (pos != -1) { //key exists
			Element e = elements[pos];
			for (int i = 0; i < e.countValues; i++) {
				s.add(e.values[i]);
			}
		}
		return s;
	}
	
	public SetADT keys() {
		SetADT s = new Set();
		s.initialize();
		for (int i = 0; i < countKeys; i++) {
			s.add(elements[i].key);
		}
		return s;
	}
}
