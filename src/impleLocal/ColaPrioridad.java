package impleLocal;

import tdaLocal.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA {
	private class Elemento {
		int valor; //igual que int valor, prioridad; 
		int prioridad;
	}
	
	private Elemento[] elementos;
	private int indice;
	
	public void inicializarCola() {
		indice = 0;
		elementos = new Elemento[100];
	}
	
	public void acolarPrioridad(int x, int prio) {
		int j = indice;
		//desplaza a derecha los elementos de la cola mientras
		//estos tengan mayor o igual prioridad que la de x
		while (j > 0 && elementos[j-1].prioridad >= prio) {
			elementos[j] = elementos[j-1];
			j--;
		}
		elementos[j] = new Elemento(); //armo la cajita
		elementos[j].valor = x;
		elementos[j].prioridad = prio;
		indice++;
	}
	
	public void desacolar() {
		//elementos[indice - 1] = null;
		indice--;
	}
	
	public int primero() {
		return elementos[indice-1].valor;
	}
	
	public int prioridad() {
		return elementos[indice-1].prioridad;
	}
	
	public boolean colaVacia() {
		return (indice == 0);
	}
}
