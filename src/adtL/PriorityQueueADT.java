package adtL;

public interface PriorityQueueADT {
	void initialize();
	void priorityEnqueue(int x, int priority); //initialized queue
	void dequeue(); //initialized and not empty queue
	int peek(); //initialized and not empty queue
	int priority(); //initialized and not empty queue
	boolean isEmpty(); //initialized queue

}
