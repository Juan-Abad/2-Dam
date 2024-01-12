package hilos;

public class miHilo2 implements Runnable{
	String nombre;
	
	public miHilo2(String nombre) {
		this.nombre=nombre;
	}
	
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("Soy el hilo "+this.nombre);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
