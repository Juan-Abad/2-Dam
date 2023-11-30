package ej09_VentaEntradas;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {

	public static void main(String[] args) {// main
		int numMaxEntradas = 200;// variable que guarda el numero de entradas a poner en venta
		Comprador comprador;// creamos la variable de la clase Comprador
		Entrada entrada = new Entrada();// creamos una entrada para inicializarlas en ese metodo estatico que tienen la
										// entradas
		entrada.inicializarEntradas(numMaxEntradas);// se inicializan las entradas, pasando el numero de entradas que se
													// pondran en venta
		for (int i = 0; i < 10; i++) {// for para crear 10 compradores
			comprador = new Comprador();// se crea el comprador
			comprador.start();// iniciamos el hilo
		}
	}

}
