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
	        synchronized (claseNombre) {
	            while (claseNombre.getNombre() != null) {
	                try {
	                    claseNombre.wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            enviar_nombre(nombre);
	            claseNombre.notifyAll(); // Notificar a todos los hilos en espera
	        }
	    }
	}

	public void enviar_nombre(String nombre) {
		synchronized (claseNombre) {
			System.out.println("Hilo ruso: "+idRuso+", introduce nombre: "+nombre);
			claseNombre.setNombre(nombre);
		}
	}
}
