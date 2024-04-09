package impleLocal;

import tdaLocal.ConjuntoTDA;

public class ConjuntoDin implements ConjuntoTDA {
	private class Nodo { //la célula de la estructura
		int info; //el valor almacenado
		Nodo sig; //la referencia al siguiente nodo
	}
	
	private Nodo c; //la referencia de la estructura
	
	public void inicializarConjunto () { //constante
		c = null; //C
	}
	
	public boolean conjuntoVacio() { //constante
		return (c == null); //C
	}
	
	public void agregar(int x) { //lineal
		if (!this.pertenece(x)) { //se verifica pertenencia //L
			Nodo nuevo = new Nodo(); //el nuevo nodo que se agregar //C
			nuevo.info = x; //C
			nuevo.sig = c; //C
			c = nuevo; //C
		}
	}
	
	public int elegir() { //arbitrario //constante
		return c.info; //elegimos el primero (puede ser cualquiera) //C
	}
	
	public void sacar(int x) { //lineal
		if (c != null) { //C
			if (c.info == x) { //es el primero //C
				c = c.sig; //C
			} 
			else { //es algún otro; lo buscamos // ->L
				Nodo aux = c; //C
				while (aux.sig != null && aux.sig.info != x) //-> L
					//C
					aux = aux.sig; //C
				if (aux.sig != null) //encontrado //C
					aux.sig = aux.sig.sig; //C
			}  
			//-> L
		}
	} //-> L
	
	public boolean pertenece(int x) { //lineal
		Nodo aux = c; //C
		while (aux != null && aux.info != x)  //-> L
			//C
			aux = aux.sig; //C
		return (aux != null); //C
	}
}