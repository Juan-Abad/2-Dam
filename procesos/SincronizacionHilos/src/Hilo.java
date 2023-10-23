
public class Hilo implements Runnable {

	private Acumulador acumulador;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Soy el hilo " + Thread.currentThread().getId() + "\nEl contador vale "+acumulador.getCantidad());
			acumulador.sumar1();
		}
		System.out.println("Soy el hilo " + Thread.currentThread().getId() + "\nEl contador vale "+acumulador.getCantidad());

	}

	public Hilo(Acumulador acumulador) {
		super();
		this.acumulador = acumulador;
	}

}
