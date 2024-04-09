package tdaLocal;

public interface ColaPrioridadTDA {
	void inicializarCola();
	void acolarPrioridad(int x, int prioridad); //cola inicializada
	void desacolar(); //cola inicializada y no vacía
	int primero(); //cola inicializada y no vacía
	int prioridad(); //cola inicializada y no vacía
	boolean colaVacia(); //cola inicializada

}
