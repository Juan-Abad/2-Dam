package ej17_amazon_black_friday;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.TreeMap;

public class Amazon {// clase Amazon
	private TreeMap<Integer, Boolean> lista_moviles = new TreeMap<Integer, Boolean>();// TreeMap lista_moviles, clave
																						// Integer(numero de movil),
																						// valor Boolean(disponibilidad
																						// del movil true=disponible
																						// para comprar)

	public Amazon() {// contructor, inicializa en el mapa los moviles disponibles, diez inicialmente
		for (int i = 0; i < 10; i++) {
			lista_moviles.put(i, true);// añade el movil, indicando en true la disponibilidad
		}
	}

	synchronized public int comprar_movil() {// metodo synchronized comprar_movil, devuelve un entero, -1 si no hay
												// moviles disponibles, si ha movil disponible devuelve numero del
												// telefono
		for (Integer index : lista_moviles.keySet()) {// recorre el mapa, iterando las claves Integer
			if (lista_moviles.get(index)) {// comprueba la disponibilidad del movil, entra si esta disponible para
											// comprar
				lista_moviles.put(index, false);// compra el movil, poniendo en false la disponibilidad
				return index;// devuelve el numero del movil comprado
			}
		}
		return -1;// devuelve -1 si no hay moviles disponibles
	}

}
