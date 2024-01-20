package ej_04_semaforo;

import java.util.concurrent.Semaphore;

public class Concierto {
	private Semaphore semaforo;

	public Concierto(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	synchronized public void comprar_entradas(int cantidad, Comprador comprador) {
		if (semaforo.availablePermits() < cantidad) {
			try {
				System.out.println("Comprador "+comprador.getId()+", esperando");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			semaforo.acquire(cantidad);
			System.out.println("Comprador " + comprador.getId() + " compra " + cantidad + " entradas, quedan "
					+ semaforo.availablePermits() + " entradas");
			comprador.setNum_entradas(comprador.getNum_entradas() + cantidad);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void vender_entradas(int cantidad, Comprador comprador) {
		semaforo.release(cantidad);
		System.out.println("Comprador " + comprador.getId() + " vende " + cantidad + " entradas , quedan "
				+ semaforo.availablePermits() + " entradas");
		comprador.setNum_entradas(comprador.getNum_entradas() - cantidad);
		notifyAll();
	}
}
