package ej16_Agentes_codificacion;

public class Americano extends Thread {
	private Nombre claseNombre;

	public Americano(Nombre claseNombre) {
		this.claseNombre = claseNombre;
	}

	public void run() {
		while (true) {
			while (claseNombre.getNombre() == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (claseNombre) {
				System.out.println(claseNombre.getNombre().toUpperCase());
				claseNombre.setNombre(null);
			}
		}
	}
}
