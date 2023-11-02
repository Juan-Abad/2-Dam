
import java.util.concurrent.Semaphore;
/*
 * Actividad: Realizar un ejercicio con tres hilos sincronizados que escriba Tic...Tac....Toc...Tic...Tac....Toc 
 */

public class TicTacSincronizado {

	public static void main(String[] args) {
		
		//Clase Semaphopre (int n,boolean b)
		// n es el número de hilos que pueden adquirir el semaforo
		// true implica que la cola de semaforos es un FIFO
		
		Semaphore s1=new Semaphore(1,true);
		Semaphore s2=new Semaphore(0,true);
		
		//metodos de la clase semaphore
		//s.acquire() adquiere uno de los permisos del semaforo si no hay disponibles el hilo se bloquea hasta conseguir uno
		//disminuye en uno el contador
		//s.release() libera el recurso incrementa en uno el contador
		
		Thread htic=new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						s1.acquire();
						System.out.println("tic");
						s2.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}});
		Thread htac=new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						s2.acquire();
						System.out.println("tac");
						s1.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}});
		htic.start();
		htac.start();
		

	}

}