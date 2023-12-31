
public class Principal {

	public static void main(String[] args) {
		
		Acumulador acumulador = new Acumulador();
		Thread [] arrayHilos = new Thread[10];
		
		for(int i = 0; i < arrayHilos.length; i++) {
			arrayHilos[i] = new Thread(new Hilo(acumulador));
			arrayHilos[i].start();
		}
		
		for(Thread h: arrayHilos) {
			try {
				h.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("El valor del contador tras finalizar es: " + acumulador.getCantidad());

	}

}
