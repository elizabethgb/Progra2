package adtL;

public interface QueueADT {
	void initialize( );
	void enqueue(int x); //initialized queue
	void dequeue( ); //initialized and not empty queue
	int peek( ); //initialized and not empty queue
	boolean isEmpty( ); //initialized queue
}
