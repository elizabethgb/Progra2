package adtL;

public interface BSTADT {
	void initialize();
	void addElem(int x); //initialized tree
	void removeElem(int x); //initialized tree
	int root(); //initialized and not empty tree
	BSTADT leftChild(); //initialized and not empty tree
	BSTADT rightChild(); //initialized and not empty tree
	boolean isEmpty(); //initialized tree
}
