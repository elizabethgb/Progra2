package impleL;

import adtL.SetADT;
import adtL.MultipleDicADT;

public class MultipleDicD implements MultipleDicADT {
	private class KeyNode {
		int key;
		ValueNode values; //reference to the list of values
		KeyNode nextKey; //reference to the next key
	}
	
	private class ValueNode {
		int value;
		ValueNode nextValue; //reference to the next value
	}
	
	private KeyNode origin; //reference to the structure
	
	public void initialize() {
		origin = null;
	}
	
	public void add(int key, int value) {
		KeyNode nK = key2KeyNode(key);
		if (nK == null) { //key doesn't exist
			nK = new KeyNode();
			nK.key = key;
			nK.nextKey = origin;
			origin = nK;
		}
		ValueNode aux = nK.values;
		while (aux != null && aux.value != value) {
			aux = aux.nextValue;
		}
		if (aux == null) { //value doesn't exist
			ValueNode vn = new ValueNode();
			vn.value = value;
			vn.nextValue = nK.values;
			nK.values = vn;
		}
	}
	
	private KeyNode key2KeyNode(int key) {
		KeyNode aux = origin;
		while (aux != null && aux.key != key) {
			aux = aux.nextKey;
		}
		return aux;
	}
	
	public void removeValue(int key, int value) {
		if (origin != null) {
			if (origin.key == key) { //it's in the first
				removeValueInNode(origin, value);
				if (origin.values == null) //it emptied
					origin = origin.nextKey;
			} else {
				KeyNode aux = origin;
				while (aux.nextKey != null && aux.nextKey.key != key)
					aux = aux.nextKey;
				if (aux.nextKey != null) {
					removeValueInNode(aux.nextKey, value);
					if (aux.nextKey.values == null) { //it emptied
						aux.nextKey = aux.nextKey.nextKey;
					}
				}
			}
		}
	}
	
	private void removeValueInNode(KeyNode node, int value) {
		if (node.values != null) {
			if (node.values.value == value) { //it's the first
				node.values = node.values.nextValue;
			} else {
				ValueNode aux = node.values;
				while (aux.nextValue != null && aux.nextValue.value != value)
					aux = aux.nextValue;
				if (aux.nextValue != null)
					aux.nextValue = aux.nextValue.nextValue;
			}
		}
	}
	
	public void remove(int key) {
		if (origin != null) {
			if (origin.key == key) { //it's the first
				origin = origin.nextKey;
			} else {
				KeyNode aux = origin;
				while (aux.nextKey != null && aux.nextKey.key != key)
					aux = aux.nextKey;
				if (aux.nextKey != null)
					aux.nextKey = aux.nextKey.nextKey;
			}
		}
	}
	
	public SetADT get(int key) {
		KeyNode kn = key2KeyNode(key);
		SetADT s = new Set();
		s.initialize();
		if (kn != null) {
			ValueNode aux = kn.values;
			while (aux != null) {
				s.add(aux.value);
				aux = aux.nextValue;
			}
		}
		return s;
	}
	
	public SetADT keys() {
		SetADT s = new Set();
		s.initialize();
		KeyNode aux = origin;
		while (aux != null) {
			s.add(aux.key);
			aux = aux.nextKey;
		}
		return s;
	}
}