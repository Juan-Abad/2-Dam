package ej16_Agentes_codificacion;

public class Ruso extends Thread {
	private Nombre claseNombre;
	private String nombre;
	private Integer idRuso;
	private static Integer idSiguiente = 1;

	public Ruso(String nombre, Nombre claseNombre) {
		this.idRuso = Ruso.idSiguiente;
		Ruso.idSiguiente++;
		this.claseNombre = claseNombre;
		this.nombre = nombre;
	}

	public void run() {
		while (true) {
			while(claseNombre.getNombre()!=null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			enviar_nombre(nombre);
		}
	}

	public void enviar_nombre(String nombre) {
		synchronized (claseNombre) {
			claseNombre.setNombre(nombre);
		}
	}
}
