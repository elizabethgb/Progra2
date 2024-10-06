package adtL;

public interface MultipleDicADT {
	void initialize();
	void add(int key, int value); //initialized dictionary
	void remove(int key); ///initialized dictionary
	void removeValue(int key, int value); ///initialized dictionary
	SetADT get(int key); //initialized dictionary
	SetADT keys(); //initialized dictionary

}
