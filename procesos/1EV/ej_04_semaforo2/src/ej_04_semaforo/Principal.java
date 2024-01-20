package ej_04_semaforo;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(200);
		Comprador comprador;
		for (int i = 0; i < 100000; i++) {
			comprador = new Comprador(semaforo);
			comprador.start();
		}
	}

}
