package hilos;

public class miHilo implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("Hola soy el hilo "+ Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
