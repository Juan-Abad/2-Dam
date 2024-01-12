import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {

	Semaphore palillo1;
	Semaphore palillo2;

	public Filosofo(String nombre, Semaphore p1, Semaphore p2) {
		super(nombre);
		this.palillo1 = p1;
		this.palillo2 = p2;
	}

	@Override
	public void run() {
		while (true) {

			pensar(getName());
			System.out.println(getName() + " quiere comer.");

			comer(getName(), palillo1, palillo2);

		}

	}

	private synchronized void comer(String nombre, Semaphore p1, Semaphore p2) {
		try {
			p1.acquire();
			System.out.println(nombre + " ha cogido el palillo " + p1.toString() + " quiere el " + p2.toString());
			Thread.sleep(500);
			p2.acquire();
			System.out.println(nombre + " está comiendo. usa los palillos " + p1.toString() + p2.toString());
			p1.release();
			System.out.println(nombre + " deja de comer. Libera palillo ." + p1.toString());
			Thread.sleep(500);
			p2.release();
			System.out.println(nombre + " deja de comer. Libera palillo ." + p2.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized void pensar(String nombre) {

		System.out.println(nombre + " está pensando mucho.");
	}

}