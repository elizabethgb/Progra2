package impleLocal;

import tdaLocal.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA {
	private int[] a;
	private int cant;
	
	public void inicializarConjunto() {
		a = new int[100];
		cant = 0;
	}
	
	public void agregar(int x) {
		if (!this.pertenece(x)){ //verificación de no pertenece
			a[cant] = x;
			cant++; //nuevo elemento
		}
	}
	
	public boolean conjuntoVacio() {
		return (cant == 0);
	}
	
	public int elegir() { //arbitrario
		/*int max = cant-1;
		int min = 0;
		int pos = (int)(Math.random() * (max-min+1) + min);
		return a[pos];*/
		return a[0]; //a[indice-1];
	}
	
	public boolean pertenece(int x) {
		int i = 0;
		while (i < cant && a[i] != x)
			i++;
		return (i < cant);
		/*boolean resp;
		if (i<cant)
			resp = true;
		else
			resp = false;	
		return resp;*/
	}
	
	public void sacar(int x) {
		int i = 0;
		while (i < cant && a[i] != x)
			i++;
		if (i < cant){ //elemento encontrado
			a[i] = a[cant-1];
			cant--;
		}
	}
}