package impleLocal;

import tdaLocal.ConjuntoTDA;
import tdaLocal.GrafoTDA;

public class Grafo implements GrafoTDA {
	private int[][] mAdy; //Matriz de adyacencia
	private int[] etiqs; //Vector para mapeo a índices
	private int cantNodos;
	
	public void inicializarGrafo() {
		mAdy = new int[100][100];
		etiqs = new int[100];
		cantNodos = 0;
	}
	
	public void agregarVertice(int v) {
		etiqs[cantNodos] = v;
		for (int i = 0; i <= cantNodos; i++) {
			mAdy[cantNodos][i] = 0; //Nueva fila en 0
			mAdy[i][cantNodos] = 0; //Nueva columna en 0
		}
		cantNodos++;
	}
	
	public void eliminarVertice(int v) {
		int ind = vert2Indice(v); //índice del vértice por eliminar
		for (int k = 0; k < cantNodos; k++)
			mAdy[k][ind] = mAdy[k][cantNodos-1]; //se “pisa” la columna...
		for (int k = 0; k < cantNodos; k++)
			mAdy[ind][k] = mAdy[cantNodos-1][k]; //... y la fila
		etiqs[ind] = etiqs[cantNodos-1];
		cantNodos--;
	}
	
	private int vert2Indice(int v) { //Mapeamos vértice a índice
		int i = cantNodos-1;
		while(i >= 0 && etiqs[i] != v)
			i--;
		return i;
	}
	
	public ConjuntoTDA vertices() {
		ConjuntoTDA vert = new Conjunto();
		vert.inicializarConjunto();
		for (int i = 0; i < cantNodos; i++) {
			vert.agregar(etiqs[i]);
		}
		return vert;
	}
	
	public void agregarArista(int v1, int v2, int peso) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = peso;
	}
	
	public void eliminarArista(int v1, int v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = 0;
	}
	
	public boolean existeArista(int v1, int v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d] != 0;
	}
	
	public int pesoArista(int v1, int v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d];
	}
}