package ej09_VentaEntradas;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.ArrayList;

public class Entrada {// Clase Entrada
	private static ArrayList<Entrada> listaEntradas = new ArrayList<Entrada>(); // Esta es una lista que guarda las(en
																				// este caso) 200 entradas a vender, es
																				// estatico para que la lista de las
																				// entradas pueda ser accedida deste el
																				// resto de entradas.

	private static Integer numeroEntradasDisponibles;// es el numero de entradas que se pueden vender
	private Integer idEntrada;// el identificador de la entrada
	private static Integer siguienteID = 1;// es el siguiente id, para hacer el claculo de los id

	private Boolean isAvailable = true;// indica el estado actual de cada entrada, si esta vendida o no

	public Entrada() {// Constructor de la clase entrada
		this.idEntrada = siguienteID;// Se crea el id de la entrada
		siguienteID++;// Se añade 1 a la variable para la siguiente entrada
	}

	public static void inicializarEntradas(int numeroMaxEntradas) {// Metodo estatico que permite inicializar las
																	// entradas, a este metodo se le indica por
																	// parametros cuantras entradas van a estar
																	// disponibles para venderse. Y ejecuta un codigo el
																	// cual en funcion de ese parametro crea un numero
																	// de entradas.
		numeroEntradasDisponibles = numeroMaxEntradas;// damos el valor del parametro a la variable interna de entrada
		for (int i = 0; i < numeroMaxEntradas - 1; i++) {// for que recorre de 0 al numero de entradas a vender
			listaEntradas.add(new Entrada());// añadimos a la lista estatica cada entrada, para no perderlas en memoria
		}
	}

	synchronized public static Integer getNumeroEntradasDisponibles() {
		return numeroEntradasDisponibles;
	}

	synchronized public static void setNumeroEntradasDisponibles(Integer numeroEntradasDisponibles) {
		Entrada.numeroEntradasDisponibles = numeroEntradasDisponibles;
	}

	synchronized public Integer getIdEntrada() {
		return idEntrada;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	synchronized public void setIsAvailable(Boolean isAvailable) {
		if (isAvailable != null) {
			this.isAvailable = isAvailable;
		}
	}

	synchronized public Boolean getIsAvailable() {
		return isAvailable;
	}

	synchronized public static ArrayList<Entrada> getListaEntradas() {
		return listaEntradas;
	}

}
