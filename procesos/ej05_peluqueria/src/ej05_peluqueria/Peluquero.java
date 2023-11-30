package ej05_peluqueria;

public class Peluquero extends Thread {
	private boolean ocupado = false;
	private Integer idPeluquero;
	private static Integer siguienteID = 1;

	public Peluquero() {
		this.idPeluquero = siguienteID;
		siguienteID++;
	}

	public void run() {
		while (true) {
			while (ocupado == true) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void atender_cliente(Cliente cliente) {
		ocupado = true;
		System.out.println("Peluquero: " + idPeluquero+" atiende a cliente: "+cliente.getIdCliente());
		try {
			sleep((int) ((Math.random() * 150) + 50));
			System.out.println("El peluquero "+idPeluquero+" termina");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
