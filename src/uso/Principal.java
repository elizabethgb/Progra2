package uso;

import imple.Cola;
import imple.ColaPrioridad;
import imple.Conjunto;
import imple.DiccionarioMultiple;
import imple.Pila;
import tda.ColaPrioridadTDA;
import tda.ColaTDA;
import tda.ConjuntoTDA;
import tda.DiccionarioMultipleTDA;
import tda.DiccionarioSimpleTDA;
import tda.GrafoTDA;
import tda.PilaTDA;
import tdaLocal.ABBTDA;

public class Principal {

	//MÉTODOS EXTERNOS

	public static PilaTDA copiarPila(PilaTDA origen) {
		PilaTDA resp = new Pila();
		resp.inicializarPila();
		//TODO hacer la copia
		return resp;
	}

	//precondición: origen y destino inicializadas
	public static void pasarCola1(ColaTDA origen, ColaTDA destino) {
		while (!origen.colaVacia()) {
			destino.acolar(origen.primero());
			origen.desacolar();
		}
	} //postcondición: cola origen se destruye

	//precondición: origen inicializada
	public static ColaTDA pasarCola2(ColaTDA origen) {
		ColaTDA resp = new Cola();
		resp.inicializarCola();
		while(!origen.colaVacia()) {
			int elem = origen.primero();
			resp.acolar(elem);
			//resp.acolar(origen.primero()); //es lo mismo
			origen.desacolar();
		}
		return resp;
	} //postcondición: origen se destruye

	//precondición: origen inicializada
	public static ColaTDA copiarCola(ColaTDA origen) { //costo polinómico
		ColaTDA copia = new Cola(); //C
		copia.inicializarCola(); //C
		ColaTDA aux = new Cola(); //C
		aux.inicializarCola(); //C
		while(!origen.colaVacia()) {
			//C
			int elem = origen.primero(); //C
			aux.acolar(elem); //L
			origen.desacolar(); //C
		} //-> n*(C+C+L+C) -> n*(L) -> P
		while(!aux.colaVacia()) {
			//C
			int elem = aux.primero(); //C
			origen.acolar(elem); //L
			copia.acolar(elem); //L
			aux.desacolar(); //C
			//-> C + C + L + L + C -> L
		} //-> P
		return copia;
		//-> C + C + C + C + P + P -> P
	} //postcondición: cola origen no se destruye

	//precondición: origen inicializada
	public static void pasarCPa2Colas(ColaPrioridadTDA origen) {
		ColaTDA valores = new Cola();
		ColaTDA prioridades = new Cola();
		valores.inicializarCola();
		prioridades.inicializarCola();
		while (!origen.colaVacia()) {
			int valor = origen.primero();
			int prioridad = origen.prioridad();
			valores.acolar(valor);
			prioridades.acolar(prioridad);
			/*es lo mismo que:
			valores.acolar(origen.primero());
			prioridades.acolar(origen.prioridad());*/
			origen.desacolar();
		}
		//TODO valores y prioridades las deberíamos usar para algo
	} //postcondición: origen se vacía

	public static boolean incluye(ConjuntoTDA c1, ConjuntoTDA c2) {
		boolean resp = true;
		while(!c2.conjuntoVacio() && resp) {
			int valor = c2.elegir();
			if(!c1.pertenece(valor))
				resp = false;
			c2.sacar(valor);
			/* no es lo mismo que (porque puede ser otro elemento el elegido):
			if(!c1.pertenece(c2.elegir()))
				resp = false;
			c2.sacar(c2.elegir());*/
		}
		return resp;
	}

	public static PilaTDA pasarDicAPilaValores(DiccionarioSimpleTDA dic) {
		PilaTDA pilaResp = new Pila();
		pilaResp.inicializarPila();
		/*ConjuntoTDA claves = new Conjunto();
		claves.inicializarConjunto();
		claves = dic.claves();*/
		ConjuntoTDA claves = dic.claves();
		while(!claves.conjuntoVacio()) {
			int clave = claves.elegir();
			/*int valor = dic.recuperar(clave);
			pilaResp.apilar(valor);*/
			pilaResp.apilar(dic.recuperar(clave));
			claves.sacar(clave);
		}
		return pilaResp;
	}

	public static DiccionarioMultipleTDA pasarDeDicSimpleAMult(DiccionarioSimpleTDA dicSim) {
		DiccionarioMultipleTDA dicMul = new DiccionarioMultiple();
		dicMul.inicializarDiccionario();
		ConjuntoTDA conjClaves = dicSim.claves();
		while(!conjClaves.conjuntoVacio()) {
			int clave = conjClaves.elegir(); //no se podría llamar abajo varias veces (pretendiendo que sea el mismo)
			int valor = dicSim.recuperar(clave);
			dicMul.agregar(clave, valor);
			conjClaves.sacar(clave);
		}
		return dicMul;
	}

	public static boolean sonDiccionariosIguales(DiccionarioSimpleTDA d1, DiccionarioSimpleTDA d2) {
		boolean resp = true;
		ConjuntoTDA clavesD1 = d1.claves();
		ConjuntoTDA clavesD2 = d2.claves();
		while(!clavesD1.conjuntoVacio() && resp) {
			int clave = clavesD1.elegir();
			clavesD1.sacar(clave);
			if (!clavesD2.pertenece(clave))
				resp = false; //una clave del d1 no está en el d2
			else {
				clavesD2.sacar(clave);
				if (d1.recuperar(clave) != d2.recuperar(clave))
					resp = false; //los valores recuperados de d1 y d2 son distintos
			}
		}
		if (!clavesD2.conjuntoVacio())
			resp = false; //había más claves en d2 que en d1
		return resp;
	}
	
	public static int cantNodos(ABBTDA arbol) {
		if (arbol.arbolVacio())
			return 0; //caso base
		else
			return 1 + cantNodos(arbol.hijoIzq()) + cantNodos(arbol.hijoDer()); //paso recursivo
	}
	
	public static int cantNodos2(ABBTDA arbol) {
		int resp = 0;
		if (!arbol.arbolVacio()) {
			int cantIzq = cantNodos(arbol.hijoIzq());
			int cantDer = cantNodos(arbol.hijoDer());
			resp = 1 + cantIzq + cantDer;
		}
		return resp;
	}
	
	public static int cantNodos3(ABBTDA a) {
		int resp = 0;
		if (!a.arbolVacio()) {
			resp += 1;
			if (!a.hijoIzq().arbolVacio())
				resp += cantNodos3(a.hijoIzq());
			if (!a.hijoDer().arbolVacio())
				resp += cantNodos3(a.hijoDer());
		} 
		return resp;
	}
	
	//precondiciones: g inicializado, verticeDestino existe en el grafo g
	public static ConjuntoTDA predecesores(GrafoTDA g, int verticeDestino) {
		ConjuntoTDA resp = new Conjunto();
		resp.inicializarConjunto();
		ConjuntoTDA vertices = g.vertices();
		while(!vertices.conjuntoVacio()) {
			int verticeOrigen = vertices.elegir();
			if(g.existeArista(verticeOrigen, verticeDestino))
				resp.agregar(verticeOrigen);
			vertices.sacar(verticeOrigen);
		}
		return resp;
	}
	//postcondiciones: devuelve el conj de los predecesores
	
	//Calcular la altura de un ABB:
	//precondición: a inicializado y no vacío
	public static int altura(ABBTDA a) {
		//1er paso (regla general): ver si a está vacío -> no califica
		//2do paso: declaramos qué devolver
		int resp;
		//3er paso: posibilidades, distintos casos (casos base/pasos recursivos)
		if(a.hijoIzq().arbolVacio() && a.hijoDer().arbolVacio()) { //es hoja
			resp = 0;
		} else {
			if (a.hijoIzq().arbolVacio()) //HD no es vacío
				resp = altura(a.hijoDer()) + 1;
			else if (a.hijoDer().arbolVacio()) //HI no es vacío
				resp = altura(a.hijoIzq()) + 1;
			else { //HD y HI no vacíos
				int altI = altura(a.hijoIzq());
				int altD = altura(a.hijoDer());
				resp = altI > altD ? altI + 1 : altD + 1; //igual con if, o con Math.max
				/*if(altI>altD)
					resp=altI+1;
				else
					resp=altD+1;*/
			}
		}
		return resp;
	}
	
	public static int altura2(ABBTDA a) {
		if(!a.arbolVacio())
			return Math.max(altura2(a.hijoIzq()), altura2(a.hijoDer())) + 1;
		else
			return -1;
	}
	
	public static void clavesOrdenadas(DiccionarioMultipleTDA dic) { //Polinómico
		ColaPrioridadTDA colaAux = new ColaPrioridad(); //C
		colaAux.inicializarCola(); //C
		/*ConjuntoTDA claves = new Conjunto();
		claves.inicializarConjunto();*/
		ConjuntoTDA claves = dic.claves(); //C+C+P -> P
		//int clave;
		while(!claves.conjuntoVacio()) {
			//C
			int clave = claves.elegir(); //C
			claves.sacar(clave); //L
			colaAux.acolarPrioridad(1, clave); //lo que está en el valor es indistinto (lo importante es la prioridad) //L
		} //-> P
		//claves ya están ordenadas dentro de las prioridades de la CP
		while(!colaAux.colaVacia()) {
			//C
			int clave = colaAux.prioridad(); //C
			System.out.println(clave); //imprimimos las claves de forma ordenada //C
			//System.out.println(dic.recuperar(clave)); //imprimimos el valor asociado a esa clave (extra) - si fuera un dic simple (sino, recorrer el conj de valores)
			colaAux.desacolar(); //C
		} //-> L
	} //-> C+C+P+P+L -> P

	public static void main(String[] args) {
		//acá se hacen las pruebas
		//se declaran los TDA, se los llena de datos, se llaman los métodos externos

		ColaTDA cola1 = new Cola();
		cola1.inicializarCola();
		cola1.acolar(25);
		cola1.acolar(83);
		cola1.acolar(0);
		cola1.acolar(2);
		ColaTDA copiaDeCola1 = copiarCola(cola1);
		//con la copia hacemos lo que tengamos que hacer
	}

}
