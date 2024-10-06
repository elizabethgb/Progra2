package impleL;

import adtL.SetADT;
import adtL.GraphADT;

public class GraphD implements GraphADT {
	private class VertexNode {
		int vertex; //stored value
		EdgeNode edges; //reference to the list of edges
		VertexNode nextVertex; //next node reference
	}

	private class EdgeNode {
		int weight; //stored value
		VertexNode destVertex; //reference to the vertex coming into
		EdgeNode nextEdge; //next node reference
	}
	
	private VertexNode origin; //reference to the structure
	
	public void initialize() {
		origin = null;
	}
	
	public void addVertex(int v) { //at the beginning of the nodes list
		VertexNode aux = new VertexNode();
		aux.vertex = v;
		aux.edges = null ;
		aux.nextVertex = origin;
		origin = aux;
	}
	
	public void addEdge(int v1, int v2, int weight) {
		VertexNode n1 = vert2Node(v1); //Searching the head...
		VertexNode n2 = vert2Node(v2); //... and the tail
		EdgeNode aux = new EdgeNode(); //at the beginning of the edges leaving v1
		aux.weight = weight;
		aux.destVertex = n2;
		aux.nextEdge = n1.edges;
		n1.edges = aux;
	}
	
	private VertexNode vert2Node(int v) {
		VertexNode aux = origin;
		while (aux != null && aux.vertex != v)
			aux = aux.nextVertex;
		return aux;
	}
	
	public void removeVertex(int v) {
		if (origin.vertex == v) //it's the origin
			origin = origin.nextVertex;
		VertexNode aux = origin;
		while (aux != null) { //removing the edges coming into v
			this.removeEdgeNode(aux, v);
			if (aux.nextVertex != null && aux.nextVertex.vertex == v)
				aux.nextVertex = aux.nextVertex.nextVertex; //if it's the node, it's removed
			aux = aux.nextVertex;
		}
	}

	private void removeEdgeNode(VertexNode node, int v) { //removes from the node the edges coming into v
		EdgeNode aux = node.edges;
		if (aux != null) {
			if (aux.destVertex.vertex == v) { //it's the first one
				node.edges = aux.nextEdge;
			} else {
				while (aux.nextEdge != null && aux.nextEdge.destVertex.vertex != v)
					aux = aux.nextEdge;
				if (aux.nextEdge != null) { //the edge is removed
					aux.nextEdge = aux.nextEdge.nextEdge;
				}
			}
		}
	}
	
	public SetADT vertices() {
		SetADT s = new Set();
		s.initialize();
		VertexNode aux = origin;
		while (aux != null) {
			s.add(aux.vertex);
			aux = aux.nextVertex;
		}
		return s;
	}
	
	public void removeEdge(int v1, int v2) {
		VertexNode n1 = vert2Node(v1);
		removeEdgeNode(n1, v2);
	}
	
	public boolean existsEdge(int v1, int v2) {
		VertexNode n1 = vert2Node(v1);
		EdgeNode aux = n1.edges;
		while (aux != null && aux.destVertex.vertex != v2)
			aux = aux.nextEdge;
		return aux != null;
	}
	
	public int edgeWeight(int v1, int v2) {
		VertexNode n1 = vert2Node(v1);
		EdgeNode aux = n1.edges;
		while (aux.destVertex.vertex != v2)
			aux = aux.nextEdge;
		return aux.weight;
	}
}