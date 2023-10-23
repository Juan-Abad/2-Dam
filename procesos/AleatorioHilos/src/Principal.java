
public class Principal {

	public static void main(String[] args) {
		NumOculto numOculto = new NumOculto();
		Thread[] arrayHilos = new Thread[10];

		for (int i = 0; i < arrayHilos.length; i++) {
			arrayHilos[i] = new Thread(new Hilo());
			arrayHilos[i].start();
		}

	}

}
