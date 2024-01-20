import java.util.concurrent.Semaphore;

public class EjemploSemaforos {

	public static void main(String[] args) {
		Thread[] hilos = new Thread[10];
		Semaphore s = new Semaphore(4, true);

		for (int i = 0; i < 10; i++) {
			int nhilo = i;
			hilos[i] = new Thread(()->funcion(nhilo, s));
		}
		for (Thread t:hilos) {t.start();}

	}

	public static void funcion(int nhilo, Semaphore s) {
		System.out.println("Hilo: " + nhilo + " entrando ....espera....");
		try {
			s.acquire();
			while (true) {
				System.out.println("hilo " + nhilo + " trabajando");
				Thread.sleep(100);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
