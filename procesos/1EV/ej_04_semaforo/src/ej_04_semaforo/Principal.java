package ej_04_semaforo;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(200);
		Concierto concierto = new Concierto(semaforo);
		Comprador comprador;
		for (int i = 0; i < 50; i++) {
			comprador = new Comprador(concierto);
			comprador.start();
		}
	}

}
