package impleLocal;

import tdaLocal.ConjuntoTDA;
import tdaLocal.DiccionarioMultipleTDA;

public class DiccionarioMultiple implements DiccionarioMultipleTDA {
	class Elemento {
		int clave;
		int[] valores;
		int cantValores;
	}
	
	/*class Elemento2 {
		int clave;
		ConjuntoTDA valores;
	}*/
	
	private Elemento[] elementos;
	private int cantClaves;
	
	public void inicializarDiccionario() {
		elementos = new Elemento[100];
		cantClaves = 0;
	}
	
	public void agregar(int clave, int valor) {
		int posC = clave2Indice(clave);
		if (posC == -1) { //la clave no existe
			elementos[cantClaves] = new Elemento(); //nueva entrada
			elementos[cantClaves].clave = clave;
			elementos[cantClaves].valores = new int[100];
			elementos[cantClaves].valores[0] = valor;
			elementos[cantClaves].cantValores = 1;
			cantClaves++;
		} else {
			Elemento e = elementos[posC];
			int posV = valor2Indice(e, valor);
			if (posV == -1) { //el valor no existe
				e.valores[e.cantValores] = valor;
				e.cantValores++;
			}
		}
	}
	
	private int clave2Indice(int clave) {
		int i = cantClaves - 1;
		while (i >= 0 && elementos[i].clave != clave)
			i--;
		return i;
	}
	
	private int valor2Indice(Elemento e, int valor) {
		int i = e.cantValores-1;
		while (i >= 0 && e.valores[i] != valor)
			i--;
		return i;
	}
	
	public void eliminar(int clave) {
		int pos = clave2Indice(clave);
		if (pos != -1) { //la clave existe
			elementos[pos] = elementos[cantClaves-1];
			cantClaves--;
		}
	}
	
	public void eliminarValor(int clave, int valor) {
		int posC = clave2Indice(clave);
		if (posC != -1) { //la clave existe
			Elemento e = elementos[posC];
			int posV = valor2Indice(e, valor);
			if (posV != -1) { //el valor existe
				e.valores[posV] = e.valores[e.cantValores-1];
				e.cantValores--;
				if (e.cantValores == 0) { //verificación final
					eliminar(clave); //se elimina la clave
				}
			}
		}
	}
	
	public ConjuntoTDA recuperar(int clave) {
		ConjuntoTDA c = new Conjunto();
		c.inicializarConjunto();
		int pos = clave2Indice(clave);
		if (pos != -1) { //la clave existe
			Elemento e = elementos[pos];
			for (int i = 0; i < e.cantValores; i++) {
				c.agregar(e.valores[i]);
			}
		}
		return c;
	}
	
	public ConjuntoTDA claves() {
		ConjuntoTDA c = new Conjunto();
		c.inicializarConjunto();
		for (int i = 0; i < cantClaves; i++) {
			c.agregar(elementos[i].clave);
		}
		return c;
	}
}
