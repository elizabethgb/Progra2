package adtL;

public interface SetADT {
	void initialize();
	void add(int x); //initialized set
	void remove(int x); //initialized set
	int select(); //initialized and not empty set
	boolean contains(int x); //initialized set
	boolean isEmpty(); //initialized set

}
