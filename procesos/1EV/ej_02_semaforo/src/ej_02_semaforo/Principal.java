package ej_02_semaforo;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore s=new Semaphore(50);
		Visitante visitante;
		
		for(int i=0; i<50; i++) {
			visitante = new Visitante(s);
			visitante.start();
		}

	}

}
