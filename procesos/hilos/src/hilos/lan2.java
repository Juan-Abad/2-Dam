package hilos;

public class lan2 {

	public static void main(String[] args) {
		
		
		Thread h1 = new Thread(new miHilo2("Pepe"));
		Thread h2 = new Thread(new miHilo2("Juan"));
		
		h1.start();
		
		h2.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Fin del hilo principal");

	}

}
