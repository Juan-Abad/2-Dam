package ej10_cocteles;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */

public class Principal {

	public static void main(String[] args) {// main
		Thread hilos_camarero;
		Camarero.getListaGrifos().add(new Grifo("Soda"));// accede al arrayList estatico de la clase camarero, añade el
															// grifo de SODA
		Camarero.getListaGrifos().add(new Grifo("Zumo"));// accede al arrayList estatico de la clase camarero, añade el
															// grifo de ZUMO
		Camarero.getListaGrifos().add(new Grifo("Tabasco"));// accede al arrayList estatico de la clase camarero, añade
															// el grifo de TABASCO

		for (int i = 0; i < 2; i++) {// for que va creando camareros y los pone a correr
			hilos_camarero = new Camarero();// crea un camarero y los guarda en el ArrayList
			hilos_camarero.start();// pone a correr el camarero
		}
	}

}
