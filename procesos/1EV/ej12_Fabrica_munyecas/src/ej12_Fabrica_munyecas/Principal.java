package ej12_Fabrica_munyecas;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {

	public static void main(String[] args) {// main
		Carro carro = new Carro();// objeto carro
		carro.start();// se pone a correr el hilo carro, para que empiece a reponer constantmente
		Trabajador[] trabajadores = new Trabajador[2];// se crea un array de 2 trabajadores

		for (int i = 0; i < 2; i++) {// for para crear los trabajadores y ponerlos a correr
			trabajadores[i] = new Trabajador(carro);// se crea el tabajador y se le pasa al contructor por parametro el
													// carro
			trabajadores[i].start();// se pone a correr el hilo trabajador
		}
	}
}
