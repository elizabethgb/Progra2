package impleLocal;

import tdaLocal.PilaTDA;

public class Pila implements PilaTDA {
	
	private int[] arr;
	private int indice;

	@Override
	public void inicializarPila() {
		arr = new int[100];
		indice = 0;
	}

	@Override
	public void apilar(int x) {
		arr[indice] = x;
		indice++; //indice+=1 //indice = indice+1
	}

	@Override
	public void desapilar() {
		indice--;
	}

	@Override
	public int tope() {
		return arr[indice-1];
	}

	@Override
	public boolean pilaVacia() {
		return indice==0;
		/*
		boolean resp;
		if (indice==0)
			resp = true;
		else
			resp = false;
		return resp;
		 */
	}

}
