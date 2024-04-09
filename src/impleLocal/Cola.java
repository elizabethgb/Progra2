package impleLocal;

import tdaLocal.ColaTDA;

public class Cola implements ColaTDA {

	private int[] arr;
	private int indice;

	@Override
	public void inicializarCola() {
		arr = new int[100];
		indice = 0;
	}

	@Override
	public void acolar(int x) {
		for (int i=indice-1; i>=0; i--) {
			arr[i+1] = arr[i];
		}
		arr[0] = x;
		indice++;
	}

	@Override
	public void desacolar() {
		indice--; //indice = indice-1 //indice -= 1
	}

	@Override
	public int primero() {
		return arr[indice-1];
	}

	@Override
	public boolean colaVacia() {
		return (indice == 0);
		/*if (indice == 0)
			return true;
		else
			return false;*/
	}

}
