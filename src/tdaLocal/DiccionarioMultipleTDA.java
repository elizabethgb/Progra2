package tdaLocal;

public interface DiccionarioMultipleTDA {
	void inicializarDiccionario();
	void agregar(int clave, int valor); //diccionario inicializado
	void eliminar(int clave); //diccionario inicializado
	void eliminarValor(int clave, int valor); //diccionario inicializado
	ConjuntoTDA recuperar(int clave); //diccionario inicializado
	ConjuntoTDA claves(); //diccionario inicializado

}
