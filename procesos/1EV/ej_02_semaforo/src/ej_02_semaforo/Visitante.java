package ej_02_semaforo;

import java.util.concurrent.Semaphore;

public class Visitante extends Thread {
	private Semaphore semaforo;
	private static int aforo_actual = 0;

	public Visitante(Semaphore s) {
		this.semaforo = s;
	}

	public void run() {
	    while (true) {
	        try {
	            int grupo = (int) ((Math.random() * 5) + 1);
	            System.out.println("Grupo " + this.getId() + ", con " + grupo + " visitantes esperando");
	            
	            semaforo.acquire(grupo);
	            
	            synchronized (Visitante.class) {
	                setAforo_actual(getAforo_actual() + grupo);
	                System.out.println(
	                        "Grupo " + this.getId() + ",con " + grupo + ", entra al acuario, aforo total: " + getAforo_actual());
	            }
	            
	            sleep(2000);
	            
	            synchronized (Visitante.class) {
	                setAforo_actual(getAforo_actual() - grupo);
	                System.out.println("Grupo " + this.getId() + ", con " + grupo + " sale del acuario");
	            }
	            
	            semaforo.release();
	            sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}


	synchronized static public int getAforo_actual() {
		return aforo_actual;
	}

	synchronized static public void setAforo_actual(int aforo_actual) {
		Visitante.aforo_actual = aforo_actual;
	}
	
}
