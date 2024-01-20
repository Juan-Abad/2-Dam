package ej_01_semaforo;

import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	Semaphore s;

	public Cliente(Semaphore s) {
		this.s = s;
	}

	public void run() {
		actuar();
	}

	synchronized void actuar() {
		try {
			System.out.println("Hilo " + this.getId() + ", esperando");
			s.acquire();
			System.out.println("Hilo " + this.getId() + ", entra");
			sleep(2000);
			System.out.println("Hilo " + this.getId() + ", sale");
			s.release();
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
