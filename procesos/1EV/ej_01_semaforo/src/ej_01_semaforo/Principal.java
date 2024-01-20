package ej_01_semaforo;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore s = new Semaphore(4);
		Cliente cliente;
		for (int i = 0; i < 20; i++) {
			cliente = new Cliente(s);
			cliente.start();
		}
	}

}
