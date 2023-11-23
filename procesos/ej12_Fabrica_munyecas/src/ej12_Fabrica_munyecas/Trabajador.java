package ej12_Fabrica_munyecas;

public class Trabajador extends Thread {
	private Carro carro;
	private Integer idTrabajador;
	private static Integer idSiguiente = 1;

	public Trabajador(Carro carro) {
		this.idTrabajador = idSiguiente;
		idSiguiente++;
		this.carro = carro;
	}

	public void run() {
		while (true) {
			while(carro.getNumero_munyecas()==0) {
				carro.notify();
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            carro.cogerDelCarro();
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Trabajador " + this.getIdTrabajador() + " ha embalado una muñeca");
		}
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Integer getIdTrabajador() {
		return idTrabajador;
	}

	public static Integer getIdSiguiente() {
		return idSiguiente;
	}
}
