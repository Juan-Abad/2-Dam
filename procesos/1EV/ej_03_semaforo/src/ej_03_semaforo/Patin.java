package ej_03_semaforo;

import java.util.concurrent.Semaphore;

public class Patin {
	private int par;
	private int numero;

	private Semaphore patin_izq;
	private Semaphore patin_dch;

	public Patin(int numero, int par) {
		this.numero = numero;
		this.par = par;
		this.patin_izq = new Semaphore(1);
		this.patin_dch = new Semaphore(1);
	}

	public int getPar() {
		return par;
	}

	public int getNumero() {
		return numero;
	}

	public Semaphore getPatin_izq() {
		return patin_izq;
	}

	public Semaphore getPatin_dch() {
		return patin_dch;
	}

}
