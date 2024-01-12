package ej05_peluqueria;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Peluquero {
	private Peluqueria peluqueria;
	private Integer idPeluquero;
	private static Integer siguienteID = 1;
	private boolean ocupado = false;

	public Peluquero(Peluqueria peluqueria) {
		this.peluqueria = peluqueria;
		this.idPeluquero = siguienteID;
		siguienteID++;
	}

	public synchronized void atenderCliente(Cliente cliente) {
		System.out.println("Peluquero atendiendo a " + cliente.getIdCliente());
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println("Peluquero terminó de atender a " + cliente.getIdCliente());
		ocupado = false;
	}

	public Peluqueria getPeluqueria() {
		return peluqueria;
	}

	public Integer getIdPeluquero() {
		return idPeluquero;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	synchronized public boolean isOcupado() {
		return ocupado;
	}

	synchronized public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
