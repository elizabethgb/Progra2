package impleL;

import adtL.SetADT;
import adtL.GraphADT;

public class Graph implements GraphADT {
	private int[][] adjM; //adjacency matrix
	private int[] tags; //Vector to map indices
	private int countNodes;
	
	public void initialize() {
		adjM = new int[100][100];
		tags = new int[100];
		countNodes = 0;
	}
	
	public void addVertex(int v) {
		tags[countNodes] = v;
		for (int i = 0; i <= countNodes; i++) {
			adjM[countNodes][i] = 0; //new row in 0
			adjM[i][countNodes] = 0; //new column in 0
		}
		countNodes++;
	}
	
	public void removeVertex(int v) {
		int ind = vert2Index(v); //index of the vertex to remove
		for (int k = 0; k < countNodes; k++)
			adjM[k][ind] = adjM[k][countNodes-1]; //the column is stepped...
		for (int k = 0; k < countNodes; k++)
			adjM[ind][k] = adjM[countNodes-1][k]; //... and the row
		tags[ind] = tags[countNodes-1];
		countNodes--;
	}
	
	private int vert2Index(int v) {
		int i = countNodes-1;
		while(i >= 0 && tags[i] != v)
			i--;
		return i;
	}
	
	public SetADT vertices() {
		SetADT vert = new Set();
		vert.initialize();
		for (int i = 0; i < countNodes; i++) {
			vert.add(tags[i]);
		}
		return vert;
	}
	
	public void addEdge(int v1, int v2, int weight) {
		int o = vert2Index(v1);
		int d = vert2Index(v2);
		adjM[o][d] = weight;
	}
	
	public void removeEdge(int v1, int v2) {
		int o = vert2Index(v1);
		int d = vert2Index(v2);
		adjM[o][d] = 0;
	}
	
	public boolean existsEdge(int v1, int v2) {
		int o = vert2Index(v1);
		int d = vert2Index(v2);
		return adjM[o][d] != 0;
	}
	
	public int edgeWeight(int v1, int v2) {
		int o = vert2Index(v1);
		int d = vert2Index(v2);
		return adjM[o][d];
	}
}