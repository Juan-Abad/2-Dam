package hilos;

public class miHilo3 implements Runnable{
	
	public miHilo3() {
		
	}

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("Soy el hilo "+Thread.currentThread().getId());
		}
		
	}
}
