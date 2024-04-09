package tdaLocal;

public interface GrafoTDA {
	void inicializarGrafo( );
	void agregarVertice(int v); //grafo inicializado y ∄ nodo
	void eliminarVertice(int v); //grafo inicializado y ∃ nodo
	ConjuntoTDA vertices(); //grafo inicializado
	void agregarArista(int v1, int v2, int peso); //grafo inicializado, ∄ arista y ∃ ambos nodos
	void eliminarArista(int v1, int v2); //grafo inicializado y ∃ arista
	boolean existeArista(int v1, int v2); //grafo inicializado y ∃ ambos nodos
	int pesoArista(int v1, int v2); //grafo inicializado y ∃ arista
}
