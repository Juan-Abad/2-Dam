package hilos;

public class lan {

	public static void main(String[] args) {
		
		Thread [] Hilos = new Thread [10];
		
		for(int i=0;i<Hilos.length;i++) {
			new Thread(new miHilo()).start();
		}
		
		System.out.println("Hilo principal finalizado");

	}

}
