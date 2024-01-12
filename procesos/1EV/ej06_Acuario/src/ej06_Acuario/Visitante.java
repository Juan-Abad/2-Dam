package ej06_Acuario;
/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.concurrent.Semaphore;

public class Visitante extends Thread {//Clase visitante extiende de Thread

	private int numMiembros;//numero de miembros de la familia
	private Semaphore e;//semaforo

	public Visitante(String nombre, int numMiembros, Semaphore e) {
		super(nombre);
		this.numMiembros = numMiembros;
		this.e = e;
	}

	public void run() {//los visitantes entran al acurario, se espera y luego salen
		System.out.println(this.getName() + " esperando a entrar con " + numMiembros + " miembros");
		try {
			e.acquire(numMiembros);//con el acquire(numero de miembros de la familia) entran, si no pueden esperan
			System.out.println(this.getName() + " entra al acuario, quedan " + e.availablePermits() + " huecos");
			sleep((int) (Math.random() * 3000));//se duerme, estan en el acuario
			System.out.println(this.getName()+" sale del acuario, salen "+numMiembros+" visitantes");
			e.release(numMiembros);//se van del acuario, hacen un release del numero de miembros de la familia
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
