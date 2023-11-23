package ej18_Reyes_Magos;

public class Buzon {
	private Integer numero_cartas_enviadas = 0;
	private boolean vacio = true;

	synchronized public void recibir_carta(Rey rey) {
		while (!this.vacio) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numero_cartas_enviadas++;
		if (rey != null) {
			System.out.println("Recibida carta: Rey" + rey.getIdRey() + " - niño " + Thread.currentThread());
		} else {
			System.out.println("Recibida carta: SinRey - " + Thread.currentThread());
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enviar_carta(rey);
		notifyAll();
	}

	public void enviar_carta(Rey rey) {
		if (rey != null) {
			System.out.println("Procesada carta: Rey" + rey.getIdRey() + " - niño " + Thread.currentThread());
		} else {
			System.out.println("Procesada carta: SinRey - " + Thread.currentThread());
		}
	}

}
