package adtL;

public interface SimpleDicADT {
	void initialize();
	void add(int key, int value); //initialized dictionary
	void remove(int key); //initialized dictionary
	int get(int key); //initialized dictionary and existing key
	SetADT keys(); //initialized dictionary
}
