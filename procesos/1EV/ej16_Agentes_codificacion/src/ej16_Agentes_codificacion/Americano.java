package ej16_Agentes_codificacion;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Americano extends Thread {
	private Nombre claseNombre;

	public Americano(Nombre claseNombre) {
		this.claseNombre = claseNombre;
	}

	public void run() {
		while (true) {
			synchronized (claseNombre) {
				while (claseNombre.getNombre() == null) {
					try {
						claseNombre.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(claseNombre.getNombre().toUpperCase());
				claseNombre.setNombre(null);
				claseNombre.notifyAll(); // Notificar a todos los hilos en espera
			}
		}
	}

}
