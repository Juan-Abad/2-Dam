package ej06_Acuario;

import java.util.concurrent.Semaphore;

public class Visitante extends Thread {

	private int numMiembros;
	private Semaphore e;

	public Visitante(String nombre, int numMiembros, Semaphore e) {
		super(nombre);
		this.numMiembros = numMiembros;
		this.e = e;
	}

	public void run() {
		System.out.println(this.getName() + " esperando a entrar con " + numMiembros + " miembros");
		try {
			e.acquire(numMiembros);
			System.out.println(this.getName() + " entra al acuario, quedan " + e.availablePermits() + " huecos");
			sleep((int) (Math.random() * 3000));
			System.out.println(this.getName()+" sale del acuario, salen "+numMiembros+" visitantes");
			e.release(numMiembros);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
