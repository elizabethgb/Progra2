package impleLocal;

import tdaLocal.ConjuntoTDA;
import tdaLocal.DiccionarioSimpleTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA {
	private class Elemento {
		int clave;
		int valor;
	}
	private Elemento[] elementos;
	private int cant;
	
	public void inicializarDiccionario() {
		cant = 0;
		elementos = new Elemento[100];
	}
	
	public void agregar(int clave, int valor) {
		int pos = this.clave2Indice(clave); //idem sin this
		if (pos == -1) { //la clave no existe -> nueva entrada
			elementos[cant] = new Elemento(); //hacemos la cajita
			elementos[cant].clave = clave;
			elementos[cant].valor = valor;
			cant++;
		} else {
			elementos[pos].valor = valor;
		}
	}
	
	private int clave2Indice(int clave) { //si no lo encuentro devuelve -1
		int i = cant - 1;
		while (i >= 0 && elementos[i].clave != clave)
			i--;
		return i;
	}
	
	public void eliminar(int clave) {
		int pos = clave2Indice(clave);
		if (pos != -1) { //la clave existe
			elementos[pos] = elementos[cant-1];
			cant--;
		}
	}
	
	public int recuperar(int clave) { //recuerden que por precondición, la clave existe
		int pos = clave2Indice(clave);
		return elementos[pos].valor;
	}
	
	public ConjuntoTDA claves() {
		ConjuntoTDA c = new Conjunto();
		c.inicializarConjunto();
		for (int i=0; i < cant; i++) {
			c.agregar(elementos[i].clave);
		}
		return c;
	}
}
