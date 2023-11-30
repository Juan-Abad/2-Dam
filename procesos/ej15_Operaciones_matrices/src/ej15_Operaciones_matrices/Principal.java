package ej15_Operaciones_matrices;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {// clase Principal, contiene el main

	public static void main(String[] args) {// main
		Matrices matrices = new Matrices();// objeto matrices de la clase Matrices, se le guarda una matriz
		Hilo hilo;// objeto hilo, sin instanciar
		Hilo.setMatrices(matrices);
		;// se le pasa a una vaariaable estatica de HILO el objeto instanciado de la
			// clase Matrices
		for (int i = 0; i < 2; i++) {// for que crea dos hilos y lo pone a correr
			hilo = new Hilo();// crea el hilo
			hilo.start();// pone a correr el hilo
		}
	}

}
