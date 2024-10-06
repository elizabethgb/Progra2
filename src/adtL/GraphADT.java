package adtL;

public interface GraphADT {
	void initialize( );
	void addVertex(int v); //initialized graph and not existing vertex
	void removeVertex(int v); //initialized graph and existing vertex
	SetADT vertices(); //initialized graph
	void addEdge(int v1, int v2, int weight); //initialized graph, not existing edge and existing vertices
	void removeEdge(int v1, int v2); //initialized graph and existing edge
	boolean existsEdge(int v1, int v2); //initialized graph and existing vertices
	int edgeWeight(int v1, int v2); //initialized graph and existing edge
}
