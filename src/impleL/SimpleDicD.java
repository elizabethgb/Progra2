package impleL;

import adtL.SetADT;
import adtL.SimpleDicADT;

public class SimpleDicD implements SimpleDicADT {
	private class KeyNode {
		int key;
		int value;
		KeyNode nextKey; //reference to the next key
	}
	
	private KeyNode origin; //reference to the structure
	
	public void initialize() {
		origin = null;
	}
	
	public void add(int key, int value) {
		KeyNode kn = key2KeyNode(key);
		if (kn == null) { //key exists
			kn = new KeyNode();
			kn.key = key;
			kn.nextKey = origin;
			origin = kn;
		}
		kn.value = value;
	}
	
	private KeyNode key2KeyNode(int key){
		KeyNode aux = origin;
		while (aux != null && aux.key != key) {
			aux = aux.nextKey;
		}
		return aux;
	}
	
	public void remove(int key) {
		if (origin != null) {
			if (origin.key == key) { //it's the first
				origin = origin.nextKey;
			} else {
				KeyNode aux = origin;
				while (aux.nextKey != null && aux.nextKey.key != key){
					aux = aux.nextKey;
				}
				if (aux.nextKey != null) {
					aux.nextKey = aux.nextKey.nextKey;
				}
			}
		}
	}
	
	public int get(int key) {
		KeyNode nk = key2KeyNode(key);
		return nk.value;
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