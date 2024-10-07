package usage;

import adtL.GraphADT;
import adtL.SetADT;
import impleL.Set;

public class GraphMethods {
	
	//GRAPH external methods
	
	//set of double adjacent vertices
	//precondition: v is a vertex of g
	public static SetADT doubleAdjacent(GraphADT g, int v) {
		SetADT resp = new Set();
		resp.initialize();
		SetADT inter = g.vertices();
		while (!inter.isEmpty()) {
			int numI = inter.select();
			SetADT dest = g.vertices();
			while (!dest.isEmpty()) {
				int numD = dest.select();
				if (g.existsEdge(v, numI) && g.existsEdge(numI, numD))
					resp.add(numD);
				dest.remove(numD);
			}
			inter.remove(numI);
		}
		return resp;
	}

	//less weight of edges coming from v
	//precondition: v is a vertex of g, it exists one edge coming from v at least
	public static int lessWeightFrom(GraphADT g, int v) {
		int resp = 0;
		SetADT vertices = g.vertices();
		while (!vertices.isEmpty()) {
			int vertex = vertices.select();
			if (g.existsEdge(v, vertex) && g.edgeWeight(v, vertex)>resp)
				resp = g.edgeWeight(v, vertex);
			vertices.remove(vertex);
		}
		return resp;
	}

	//set of predecessors of the vertex destiny
	//preconditions: g initialized, destiny exists in the graph g
	public static SetADT predecessors(GraphADT g, int destiny) {
		SetADT resp = new Set();
		resp.initialize();
		SetADT vertices = g.vertices();
		while(!vertices.isEmpty()) {
			int origin = vertices.select();
			if(g.existsEdge(origin, destiny))
				resp.add(origin);
			vertices.remove(origin);
		}
		return resp;
	}
	//postconditions: the method returns the predecessors set

	//set of isolated vertices
	public static SetADT isolated(GraphADT g) {
		SetADT resp = new Set();
		resp.initialize();
		SetADT vertices = g.vertices();
		while (!vertices.isEmpty()) {
			int vertex = vertices.select();
			boolean isIsolated = true;
			SetADT aux = g.vertices();
			while (!aux.isEmpty() && isIsolated) {
				int num = aux.select();
				if (g.existsEdge(vertex, num) || g.existsEdge(num, vertex))
					isIsolated = false;
				aux.remove(num);
			}
			if (isIsolated)
				resp.add(vertex);
			vertices.remove(vertex);
		}
		return resp;
	}

	//set of bridges vertices between v1 and v2
	//precondition: v1 and v2 are vertices of g
	public static SetADT bridges(GraphADT g, int v1, int v2) {
		SetADT resp = new Set();
		resp.initialize();;
		SetADT vertices = g.vertices();
		while (!vertices.isEmpty()) {
			int vertex = vertices.select();
			if (g.existsEdge(v1, vertex) && g.existsEdge(vertex, v2))
				resp.add(vertex);
			vertices.remove(vertex);
		}
		return resp;
	}

	//degree of vertex v
	//precondition: v is a vertex of g
	public static int degree(GraphADT g, int v) {
		int resp = 0;
		SetADT vertices = g.vertices();
		while (!vertices.isEmpty()) {
			int vertex = vertices.select();
			if (g.existsEdge(v, vertex))
				resp++;
			if (g.existsEdge(vertex, v))
				resp--;
			vertices.remove(vertex);
		}
		return resp;
	}

	public static void main(String[] args) {
		//test place
		//ADTs are declared, they are loaded with data, external methods are called

	}

}
