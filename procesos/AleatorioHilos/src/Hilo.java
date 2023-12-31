
public class Hilo implements Runnable {

	private static Boolean acertar = true;
	private Integer numAleatorio;
	private NumOculto numOculto;

	@Override
	public void run() {
		synchronized (numOculto) {
			while (acertar) {
				numAleatorio = ((int) (Math.random() * 9 + 1));

				System.out.println(
						"Soy el hilo " + Thread.currentThread().getId() + ", voy a probar el num:" + numAleatorio);
				switch (numOculto.adviniarNumero(numAleatorio)) {
				case 0:
					numOculto.notifyAll();
					try {
						numOculto.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				case -1:
					detener();
					break;
				case 1:
					System.out.println("He acertado, soy :" + Thread.currentThread().getId() + ", num mio: "
							+ numAleatorio + ", numOculto: " + numOculto.getNumOculto());
					numOculto.notifyAll();
				}
			}
		}

	}

	public Hilo() {
		super();
		acertar = true;
		numOculto = new NumOculto();
	}

	public void detener() {
		Hilo.acertar = false;
	}

}
